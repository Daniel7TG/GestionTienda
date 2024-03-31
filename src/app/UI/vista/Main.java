package app.UI.vista;

public class Main
{
  public static void main(String[] args)
  {
    javax.swing.JFrame jf = new javax.swing.JFrame("A table with components");
    jf.setLayout(new java.awt.BorderLayout());
    jf.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    jf.add(new TableWithCompsPanel(), java.awt.BorderLayout.CENTER);
    jf.setVisible(true);
  }
}

class TableWithCompsPanel extends java.awt.Container
{
  private Class<?> tableColumnClassArray[];
  private javax.swing.JTable jTableWithComps;
  private Object tableContentsArray[][];

  public TableWithCompsPanel()
  {
    tableContentsArray = new Object[][]
      {
        {"This is plain text",                                            new javax.swing.JButton("This is a button")    },
        {new javax.swing.JLabel("This is an improperly rendered label!"), new javax.swing.JCheckBox("This is a checkbox")}
      };
    tableColumnClassArray = new Class<?>[]{String.class, java.awt.Component.class};
    initGUI();
  }

  private void initGUI()
  {
    setLayout(new java.awt.BorderLayout());
    jTableWithComps = new javax.swing.JTable(new javax.swing.table.AbstractTableModel()
      {
        @Override public int getRowCount()
        {
          return tableContentsArray.length;
        }

        @Override public int getColumnCount()
        {
          return tableContentsArray[0].length;
        }

        @Override public Object getValueAt(int rowIndex, int columnIndex)
        {
          return tableContentsArray[rowIndex][columnIndex];
        }

        @Override public Class<?> getColumnClass(int columnIndex)
        {
          return tableColumnClassArray[columnIndex];
        }
      });
    jTableWithComps.setDefaultRenderer(java.awt.Component.class, new javax.swing.table.TableCellRenderer()
    {
      @Override public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
      {
        return value instanceof java.awt.Component ? (java.awt.Component)value : new javax.swing.table.DefaultTableCellRenderer();
      }
    });
    add(jTableWithComps, java.awt.BorderLayout.CENTER);
  }
}