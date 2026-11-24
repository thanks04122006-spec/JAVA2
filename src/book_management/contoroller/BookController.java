package book_management.contoroller;

import book_management.domain.Book;
import book_management.repository.BookRepository;
import book_management.view.BookInsertPan;
import book_management.view.BookSearchPan;
import book_management.view.BookUpdatePan;
import book_management.view.center.SetFrame;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BookController extends JFrame {
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    BookRepository repository;
    ArrayList<Book> bookList;
    JComboBox<String> combo;
    BookSearchPan searchPan;
    BookInsertPan insertPan;
    BookUpdatePan updatePan;
    JTable updateTable;

    public BookController() {
//        도서정보 검색==========================================================
        searchPan = new BookSearchPan();
        combo = searchPan.getCombo();
        repository = new BookRepository();
        bookList = repository.select(searchPan.getSearchWard(), combo.getSelectedIndex());
        searchPan.setBookList(bookList);
        searchPan.initView();


//      도서삽입(추가)==========================================================
        insertPan = new BookInsertPan();
        insertPan.setBookList(bookList);
        insertPan.initView();

        JButton btnSearch = searchPan.getBtnSearch();
        btnSearch.addActionListener(btnSearchListener);

        JButton btnInsert = insertPan.getBtnInsert();
        btnInsert.addActionListener(btnInsertListener);

//        도서 수정 및 삭제 =========================================================
        updatePan = new BookUpdatePan();
        updatePan.setBookList(bookList);
        updatePan.initView();

        updateTable = updatePan.getTable();
        updateTable.addMouseListener(updateTableListener);

        JButton btnUpdate = updatePan.getBtnUpdate();
        btnUpdate.addActionListener(btnupdateListener);

//        JTabbedPane에 JPanl 추가==========================================================
        tabbedPane.add("도서 검색", searchPan);
        tabbedPane.add("도서 추가", insertPan);
        tabbedPane.add("수정 및 삭제", updatePan);


        add(tabbedPane);
        tabbedPane.addMouseListener(ChangeTabbedPaneListner);

        SetFrame.setFrame(this, "도서관리 시스템", 700, 600);

    }

    ActionListener btnSearchListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            bookList = repository.select(searchPan.getSearchWard(), combo.getSelectedIndex());
            searchPan.setBookList(bookList);
            searchPan.putSearchResult();
        }
    };

    ActionListener btnInsertListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Book book = insertPan.getBook();
            repository.Insert(book);
            ArrayList<Book> InsertBookList = new ArrayList<Book>();
            InsertBookList = repository.select("", 0);
            insertPan.setBookList(InsertBookList);
            insertPan.putSearchResult();
        }
    };

    MouseAdapter updateTableListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 1){
                int rowIndex = updateTable.getSelectedRow();
                updatePan.setTextField(rowIndex);
            }

            if (e.getClickCount() == 2) {
                int result = JOptionPane.showConfirmDialog(BookController.this,"정말로 삭제하시겠습니까?", "삭제",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(result == 0) {           // 0이면 YES 1이면 NO
                    DefaultTableModel model = updatePan.getModel();
                    int rowIndex = updateTable.getSelectedRow();
                    String isbn = model.getValueAt(rowIndex, 0) + "";
                    repository.delete(isbn);
                    bookList = repository.select("", 0);
                    updatePan.setBookList(bookList);
                    updatePan.putSearchResult();
                }
            }
        }
    };

    ActionListener btnupdateListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Book book = updatePan.getBook();
            repository.update(book);
            ArrayList<Book> updateBookList = new ArrayList<>();
            updateBookList = repository.select("", 0);
            updatePan.setBookList(updateBookList);
            updatePan.putSearchResult();
        }
    };

    MouseAdapter ChangeTabbedPaneListner = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int tabIndex = tabbedPane.getSelectedIndex();
            bookList = repository.select("",0);
            switch (tabIndex) {
                case 0:
                    searchPan.setBookList(bookList);
                    searchPan.putSearchResult();
                    break;
                case 1:
                    insertPan.setBookList(bookList);
                    insertPan.putSearchResult();
                    break;
                case 2:
                    updatePan.setBookList(bookList);
                    updatePan.putSearchResult();
                    break;
            }
        }
    };
    public static void main(String[] args) {
        new BookController();
    }
}
