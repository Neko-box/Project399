package Servlets;

import Models.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet(name = "PostSendServlet", urlPatterns = "/posts")
public class PostSendServlet extends HttpServlet {
    private static ArrayList<Post> posts = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        Random rand = new Random();
        String image = "/Images/123.jpg";

        Post newPost = new Post(title, text, image);
        posts.add(newPost);
        System.out.println(title + text + image);
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
}
