package app.UI.vista.listado;

import app.interfaces.Listable;
import app.interfaces.Service;
import app.util.TableModel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.List;

public class Listado extends JPanel {

    public JTable table;
    private TableModel model;
    private Service<? extends Listable> service;

    public Listado(Map<String, Integer> columns, List<? extends Listable> data, int listColumn) {
        setLayout(new GridLayout(1, 1, 0, 0));

        String[] columnNames = columns.keySet().toArray(String[]::new);
        int[] columnWeights = columns.values().stream().mapToInt(i -> i).toArray();

        table = new JTable();
        model = new TableModel(table, data, columnNames);
        table.setModel(model);

        if(listColumn >= 0)
            model.renderListColumn(listColumn);

        model.configurarTabla(columnWeights);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(tableScroll);
    }
    public Listado(Map<String, Integer> columns, List<? extends Listable> data) {
        this(columns, data, -1);
    }

    public Listado(Map<String, Integer> columns, Service<? extends Listable> service) {
        this(columns, service, -1);
    }
    public Listado(Map<String, Integer> columns, Service<? extends Listable> service, int listColumn) {
        this(columns, service.getAll(), listColumn);
        this.service = service;
    }

    public void update() {
        model.update(service.getAll());
    }


}
