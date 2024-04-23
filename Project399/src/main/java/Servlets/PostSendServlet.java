package Servlets;

import Models.Post;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(name = "PostSendServlet", urlPatterns = "/posts")
public class PostSendServlet extends HttpServlet {
    private static ArrayList<Post> posts = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String title = req.getParameter("title");
//        String text = req.getParameter("text");
//        Random rand = new Random();
//        String image = "/Images/123.jpg";
//
//        Post newPost = new Post(title, text, image);
//        posts.add(newPost);
//        System.out.println(title + text + image);
//        req.setAttribute("posts", posts);
//
//        req.getRequestDispatcher("/posts.jsp").forward(req, resp);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        String fileName = null;
        String savePath = null;
        List<String> postItems = new ArrayList<>();
        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item: items) {
                if (item.isFormField()) {
                    String value = item.getString();
                    postItems.add(value);
                } else {
                    InputStream stream = item.getInputStream();
                    fileName = item.getName();

                    savePath = getServletContext().getRealPath("/Images");
                    File path = new File(savePath);
                    uploadFile(stream, path, fileName);
                }
            }
        } catch (Exception e) {
            System.out.println("File upload failed due to: " + e);
        }
        String title = postItems.get(0);
        String text = postItems.get(1);

        posts.add(new Post(title, text, "/Images/" + fileName));
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/posts.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        posts.clear();
        preSetPost();
        req.setAttribute("posts", posts);

        req.getRequestDispatcher("/posts.jsp").forward(req, resp);
    }

    private static void preSetPost() {
        Post p1 = new Post("Test", "testtesttesttest", "/Images/123.jpg");
        posts.add(p1);
    }

    private static void uploadFile(InputStream filestream,File savaPath,String filename) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        filename = filename.substring(filename.lastIndexOf("\\")+1);
        String realSavePath = savaPath+"\\"+filename;

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(realSavePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        int len = 0;

        try {
            while((len=filestream.read(buffer))>0){
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            filestream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
