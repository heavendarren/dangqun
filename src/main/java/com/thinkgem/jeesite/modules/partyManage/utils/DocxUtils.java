package com.thinkgem.jeesite.modules.partyManage.utils;

import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DocxUtils {
	public static final String TargetDir = "targetfile" + File.separator;
	public static final String DownloadDir = "downloadfile" + File.separator;

	public static ResponseEntity<byte[]> fileDownload(File targetfile,
			String filename, HttpServletRequest request) {
		try {
			if (targetfile.exists() && targetfile.isFile()) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				String userAgent = request.getHeader("User-Agent");
				byte[] bytes = userAgent.contains("MSIE") ? filename.getBytes()
						: filename.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
				String fileName = new String(bytes, "ISO-8859-1");
				headers.set(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + fileName + "\"");
				byte[] readFileToByteArray = null;
				readFileToByteArray = FileUtils.readFileToByteArray(targetfile);
				return new ResponseEntity<byte[]>(readFileToByteArray, headers,
						HttpStatus.OK);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static String getDocxPath(HttpServletRequest request, String type,
			Map<String, String> params) {

		Map<String, String> docxMapping = SpringContextHolder
				.getBean("docxMapping");
		String fileTargetPath = docxMapping.get(type);
		String tf = null;
		if (fileTargetPath != null) {
			File dir = new File(request.getSession().getServletContext()
					.getRealPath("/")
					+ DownloadDir + UserUtils.getUser().getId());
			if (!dir.exists())
				dir.mkdirs(); // 如果目标文件夹不存在则创建新的文件夹
			InputStream is = null;
			OutputStream os = null;

			try {
				is = new FileInputStream(request.getSession()
						.getServletContext().getRealPath("/")
						+ TargetDir + fileTargetPath);
				XWPFDocument doc = new XWPFDocument(is);
				replaceInTable(doc, params, "");
				tf = dir.getPath()
						+ File.separator
						+ UUID.randomUUID().toString().replace("-", "")
								.concat(".docx");
				os = new FileOutputStream(tf);
				doc.write(os);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return tf;
	}

	private static void replaceInTable(XWPFDocument doc,
			Map<String, String> params, String destFilePath) {
		Iterator<XWPFTable> iterator = doc.getTablesIterator();
		XWPFTable table;
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		List<XWPFParagraph> paras;
		while (iterator.hasNext()) {
			table = iterator.next();
			rows = table.getRows();
			for (XWPFTableRow row : rows) {
				cells = row.getTableCells();
				for (XWPFTableCell cell : cells) {
					paras = cell.getParagraphs();
					for (XWPFParagraph para : paras) {
						replaceInPara(para, params, destFilePath, doc, cell);
					}
				}
			}
		}
		Iterator<XWPFParagraph> itPara = doc.getParagraphsIterator();
		XWPFParagraph paragraph;
		while (itPara.hasNext()) {
			paragraph = (XWPFParagraph) itPara.next();
			replaceInPara(paragraph, params, destFilePath, doc, null);
		}
	}

	private static void replaceInPara(XWPFParagraph para,
			Map<String, String> params, String destFilePath, XWPFDocument doc,
			XWPFTableCell cell) {
		List<XWPFRun> runs = para.getRuns();
		for (int i = 0; i < runs.size(); i++) {
			XWPFRun run = runs.get(i);
			String runText = run.toString();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String key = entry.getKey();
				if (key.equals(runText.trim())) {
					String ff = run.getFontFamily();
					int fs = run.getFontSize();
					para.removeRun(i);
					XWPFRun newrun = para.insertNewRun(i);
					newrun.setText(entry.getValue());
					newrun.setFontFamily(ff);
					newrun.setFontSize(fs);
				} else {
					continue;
				}
			}
		}
	}
}
