package common;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
@WebServlet("/ckeditorImg.do")

public class CKEditorUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CKEditorUpload() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		utilc uc = new utilc().encoding(request, response).syo("파일 업로드 요청");
		MultipartRequest multi = uc.getMultipart(request); //MultiPartRequest 받아옴과 동시에 폴더 생성
		String file = null;
		try {
		if(multi!=null) {
			file = uc.imgfile(request, multi, "upload");
			System.out.println("fileName : "+file);
		}else {
			uc.syo("MultiPart 요청 아님");
		}}catch(Exception e) {uc.syo("예외 발생");}
		JsonObject json = new JsonObject();
		json.addProperty("uploaded", 1);
		json.addProperty("fileName", (!uc.nulchk(file))?file:"");
		json.addProperty("url",(uc.nulchk(file)?"":uc.upload+"/"+file));
		PrintWriter printWriter = response.getWriter();
		System.out.println(json);
		printWriter.println(json);
		uc.close(printWriter);
		return;
	}
}
