package com.siana.workshareapp.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.AbstractView;

import com.siana.workshareapp.common.exception.FileException;
import com.siana.workshareapp.common.vo.FileVO;


public class FileUtil extends AbstractView{

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	@Value("${file.uploadBaseDir}")
	private String uploadBaseDir;

	/**
	 * NAME : uploadFiles
	 * DESC : 서버에 파일을 업로드 후, 해당 파일의 정보를 Map에 담은 리스트를 리턴한다
	 * DATE : 2020. 5. 13.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @return uploadedFileList
	 * @throws FileException
	 * </pre>
	 */
	public List<Map<String,String>> uploadFiles(MultipartHttpServletRequest request) throws FileException{
		List uploadedFileList = new ArrayList();
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		String path = getUploadPath(contextPath);

		Iterator<String> itr  = request.getFileNames();
		while(itr.hasNext()) {
			String inputNm = itr.next().toString();
			for(MultipartFile mf : request.getFiles(inputNm)) {
				int pos = mf.getOriginalFilename().lastIndexOf( "." ); // 마지막 . 의 위치
				String ext = mf.getOriginalFilename().substring( pos + 1 ); // 확장자
				File file = new File(path+ new File("").separator + getFileNm() + "." + ext);

				while(file.exists()) {
					file = new File(path+ new File("").separator + getFileNm() + "." + ext);
				}
				try {
					mf.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
					throw new FileException("파일생성 중 오류가 발생했습니다.");
				} catch (IOException e) {
					e.printStackTrace();
					throw new FileException("파일생성 중 오류가 발생했습니다.");
				}
//				Map fileMap = getFileMap(file,mf.getOriginalFilename(),inputNm);
				uploadedFileList.add(new FileVO(inputNm, mf.getOriginalFilename(), file.getName(), file.getPath(), mf.getSize()));
			}
		}
		if(uploadedFileList.isEmpty()) throw new FileException("파일업로드 중 오류가 발생했습니다.",200);
		return uploadedFileList;
	}

	/**
	 * NAME : getFileMap
	 * DESC : 업로드 된 파일의 정보를 맵에 담아 리턴한다.
	 * DATE : 2020. 5. 13.
	 * <pre>
	 * @auther jyh
	 * @param file
	 * @param oNm
	 * @param elNm
	 * @return fileMap
	 * </pre>
	 */
	private Map getFileMap(File file,String oNm,String elNm) {
		Map fileMap = new HashMap();
		fileMap.put("elNm",elNm);
		fileMap.put("oNm",oNm);
		fileMap.put("sNm",file.getName());
		fileMap.put("path",file.getPath());
		logger.debug("fileMap ::>" , fileMap.toString());
		return fileMap;
	}

	/**
	 * NAME : getFileNm
	 * DESC : 파일명 생성
	 * DATE : 2020. 5. 13.
	 * <pre>
	 * @auther jyh
	 * @return fileNm
	 * </pre>
	 */
	private String getFileNm() {
		String fileNm = UUID.randomUUID().toString();
		return fileNm;
	}

	/**
	 * NAME : getPath
	 * DESC : 파일 저장 경로를 생성하고 해당 경로를 스트링으로 리턴한다.
	 * DATE : 2020. 5. 13.
	 * <pre>
	 * @auther jyh
	 * @return dirNm
	 * </pre>
	 */
	private String getUploadPath(String contextPath) {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dirNm = format.format(now);
		File dir = new File(contextPath+uploadBaseDir+dirNm);
		if(!dir.exists()) dir.mkdirs();
		return dir.getPath();
	}

	/**
	 * NAME : deleteFile
	 * DESC : 파일 삭제
	 * DATE : 2020. 5. 13.
	 * <pre>
	 * @auther jyh
	 * @param filePath
	 * @return result 정상삭제 여부
	 * </pre>
	 */
	public boolean deleteFile(String filePath) {
		int result = 0;
		File file = new File(filePath);
		if(file.exists()) {
			file.delete();
		}else {
			new FileException("존재하지 않는 파일입니다.("+file.getName()+")",300);
		}
		return !file.exists();
	}

	public void Download(){
		setContentType("application/download; utf-8");
	} //파일 다운로드

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = (File)model.get("downloadFile");
        System.out.println("DownloadView --> file.getPath() : " + file.getPath());
        System.out.println("DownloadView --> file.getName() : " + file.getName());

        response.setContentType(getContentType());
        response.setContentLength((int)file.length());

        String fileName = null;

        //if(ie){
        //브라우저 정보에 따라 utf-8변경
        if(request.getHeader("User-Agent").indexOf("MSIE") > -1){
            fileName = URLEncoder.encode(file.getName(), "utf-8");
        } else {
            fileName = new String(file.getName().getBytes("utf-8"));
        }// end if;

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;

        //파일 카피 후 마무리
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(Exception e){}
            }
        }// try end;
        out.flush();
	}
}
