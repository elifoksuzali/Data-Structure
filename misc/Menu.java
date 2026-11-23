package projedenemesi_4;

import javax.swing.JMenu;

public class Menu extends JMenu {

    public Menu(String name) {
        setText(name);
    }

    public void addMenuItem(MenuItem menuItem) {
        add(menuItem);
    }

    public void addMenu(Menu menu) {
        add(menu);
    }
}
