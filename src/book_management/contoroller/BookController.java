package book_management.contoroller;

import book_management.domain.Book;
import book_management.repository.BookRepository;
import book_management.view.BookSearchPan;
import book_management.view.center.SetFrame;

import javax.swing.*;
import java.util.ArrayList;

public class BookController extends JFrame {
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    BookRepository repository;
    ArrayList<Book> bookList;
    JComboBox<String> combo;

    public BookController() {
        BookSearchPan bookSearchPan = new BookSearchPan();
        combo = bookSearchPan.getCombo();
        repository = new BookRepository();
        bookList = repository.select(bookSearchPan.getSearchWard(), combo.getSelectedIndex());
        bookSearchPan.setBookList(bookList);
        bookSearchPan.initView();

        tabbedPane.add("도서 검색", bookSearchPan);

        add(tabbedPane);

        SetFrame.setFrame(this, "도서관리 시스템", 700, 600);

    }

    public static void main(String[] args) {
        new BookController();
    }
}
