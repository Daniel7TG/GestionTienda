package app.UI.vista.listado;

import app.interfaces.Listable;
import app.interfaces.Service;
import app.util.TableModel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Listado extends JPanel {

    public JTable table;

    public Listado(Map<String, Integer> columns, Service<? extends Listable> service) {
        this(columns, service, -1);
    }
    public Listado(Map<String, Integer> columns, Service<? extends Listable> service, int listColumn) {

        setLayout(new GridLayout(1, 1, 0, 0));

        String[] columnNames = columns.keySet().toArray(String[]::new);
        int[] columnWeights = columns.values().stream().mapToInt(i -> i).toArray();

        table = new JTable();
        TableModel model = new TableModel(table, service.getAll(), columnNames);
        table.setModel(model);

        if(listColumn >= 0)
            model.renderListColumn(listColumn);

        model.configurarTabla(columnWeights);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(tableScroll);
    }

}
