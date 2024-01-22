import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class Store {

	private JFrame frame;
	private JTextField txtPID;
	private JTextField txtPname;
	private JTextField txtEdition;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JTable table;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public void updateTable()
	{
		conn = StoreData.ConnectDB();
		
		if (conn != null)
		{
			String sql = "Select ProductID, Product,Edition,Price,Quantity,Date of sales,Status,Quality";
		
		
		try
		{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			Object[] columnData = new Object[8];
			
			while(rs.next()) {
				columnData [0] = rs.getString("ProductID");
				columnData [1] = rs.getString("Product");
				columnData [2] = rs.getString("Edition");
				columnData [3] = rs.getString("Price");
				columnData [4] = rs.getString("Quantity");
				columnData [5] = rs.getDate("Date of sales");
				columnData [6] = rs.getString("Status");
				columnData [7] = rs.getString("Quality");
				
				model.addRow(columnData);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
}		
			
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Store window = new Store();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Store() {
		initialize();
		}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(127, 255, 212));
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.setBounds(100, 100, 1235, 729);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new MatteBorder(7, 7, 7, 7, (Color) new Color(0, 255, 255)));
		panel.setBounds(20, 105, 475, 490);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(39, 30, 88, 30);
		panel.add(lblNewLabel);
		
		JLabel lblProductName = new JLabel("Product name");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProductName.setBounds(39, 83, 88, 30);
		panel.add(lblProductName);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEdition.setBounds(39, 136, 88, 30);
		panel.add(lblEdition);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrice.setBounds(39, 188, 88, 30);
		panel.add(lblPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantity.setBounds(39, 242, 88, 30);
		panel.add(lblQuantity);
		
		JLabel lblDateOfSales = new JLabel("Date of sales");
		lblDateOfSales.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDateOfSales.setBounds(39, 296, 88, 30);
		panel.add(lblDateOfSales);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(39, 347, 88, 30);
		panel.add(lblStatus);
		
		JLabel lblQuality = new JLabel("Quality");
		lblQuality.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuality.setBounds(39, 397, 88, 30);
		panel.add(lblQuality);
		
		txtPID = new JTextField();
		txtPID.setBounds(161, 34, 153, 26);
		panel.add(txtPID);
		txtPID.setColumns(10);
		
		txtPname = new JTextField();
		txtPname.setColumns(10);
		txtPname.setBounds(161, 87, 270, 26);
		panel.add(txtPname);
		
		txtEdition = new JTextField();
		txtEdition.setColumns(10);
		txtEdition.setBounds(161, 136, 270, 26);
		panel.add(txtEdition);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(161, 188, 270, 26);
		panel.add(txtPrice);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(161, 242, 270, 26);
		panel.add(txtQuantity);
		
		JComboBox txtStatus = new JComboBox();
		txtStatus.setModel(new DefaultComboBoxModel(new String[] {"Sold", "Available"}));
		txtStatus.setBounds(161, 347, 79, 27);
		panel.add(txtStatus);
		
		JComboBox txtQuality = new JComboBox();
		txtQuality.setModel(new DefaultComboBoxModel(new String[] {"Used", "New"}));
		txtQuality.setBounds(161, 400, 79, 27);
		panel.add(txtQuality);
		
		JButton btnNewButton_1 = new JButton("FIND");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(352, 33, 79, 26);
		panel.add(btnNewButton_1);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(296, 347, 135, 26);
		panel.add(btnDelete);
		
		JDateChooser txtDate = new JDateChooser();
		txtDate.setBounds(161, 300, 176, 26);
		panel.add(txtDate);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(296, 403, 135, 26);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            // Lấy chỉ số hàng được chọn trong bảng
		            int selectedRow = table.getSelectedRow();

		            // Kiểm tra xem có hàng nào được chọn không
		            if (selectedRow == -1) {
		                JOptionPane.showMessageDialog(frame, "Vui lòng chọn một hàng để cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Lấy dữ liệu từ các trường nhập liệu
		            String pid = txtPID.getText();
		            String pname = txtPname.getText();
		            String edition = txtEdition.getText();
		            String price = txtPrice.getText();
		            String quantity = txtQuantity.getText();
		            String status = txtStatus.getSelectedItem().toString();
		            String quality = txtQuality.getSelectedItem().toString();
		            
		            // Lấy ngày từ JDateChooser
		            java.util.Date utilDate = txtDate.getDate();
		            java.sql.Date date = new java.sql.Date(utilDate.getTime());

		            // Kiểm tra xem có trường nào trống không
		            if (pid.isEmpty() || pname.isEmpty() || edition.isEmpty() || price.isEmpty() || quantity.isEmpty() || date == null || status.isEmpty() || quality.isEmpty()) {
		                JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Chuyển đổi các giá trị cần thiết thành kiểu phù hợp
		            int intPrice = Integer.parseInt(price);
		            int intQuantity = Integer.parseInt(quantity);

		            // Cập nhật dữ liệu của hàng được chọn trong bảng
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            model.setValueAt(pid, selectedRow, 0);
		            model.setValueAt(pname, selectedRow, 1);
		            model.setValueAt(edition, selectedRow, 2);
		            model.setValueAt(intPrice, selectedRow, 3);
		            model.setValueAt(intQuantity, selectedRow, 4);
		            model.setValueAt(date, selectedRow, 5);
		            model.setValueAt(status, selectedRow, 6);
		            model.setValueAt(quality, selectedRow, 7);

		            // Xóa dữ liệu từ các trường nhập liệu sau khi cập nhật
		            txtPID.setText(null);
		            txtPname.setText(null);
		            txtEdition.setText(null);
		            txtPrice.setText(null);
		            txtQuantity.setText(null);
		            txtDate.setDate(null);
		            txtStatus.setSelectedIndex(0);
		            txtQuality.setSelectedIndex(0);

		            JOptionPane.showMessageDialog(frame, "Dữ liệu đã được cập nhật thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(frame, "Vui lòng nhập giá và số lượng là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		
				
	
	
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageFormat header = new MessageFormat("Printing in Progress");
				MessageFormat footer = new MessageFormat("Page {0,number,integer}");
				
				try
				{
					table.print();
				}
				catch(java.awt.print.PrinterException ev)
				{
					System.err.format("No Printer found",ev.getMessage());
				}
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPrint.setBounds(376, 617, 135, 50);
		frame.getContentPane().add(btnPrint);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPID.setText(null);
				txtPname.setText(null);
				txtEdition.setText(null);
				txtPrice.setText(null);
				txtQuantity.setText(null);
				txtDate.setCalendar(null);
				txtStatus.setSelectedIndex(0);
				txtQuality.setSelectedIndex(0);
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReset.setBounds(665, 617, 135, 50);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit","Storedata Database system",
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
			
			
		});
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findProductByID();
            }
        });
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.setBounds(948, 617, 135, 50);
		frame.getContentPane().add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(518, 115, 680, 471);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(null);
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product ID", "Product Name", "Edition", "Price", "Quantity", "Date of sales", "Status", "Quality"
			}
		));
		 btnDelete.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    int selectedRow = table.getSelectedRow();

	                    if (selectedRow != -1) {
	                        DefaultTableModel model = (DefaultTableModel) table.getModel();
	                        model.removeRow(selectedRow);
	                    } else {
	                        JOptionPane.showMessageDialog(frame, "Vui lòng chọn một hàng để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    }

	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
	
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("COMPUTER COMPONENT STORE");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(379, 23, 503, 50);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(7, 7, 7, 7, (Color) new Color(0, 255, 255)));
		panel_1.setBounds(505, 105, 706, 490);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("ADD NEW\r\n");
		btnNewButton.setBounds(110, 617, 135, 50);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            // Lấy dữ liệu từ các trường nhập liệu
		            String pid = txtPID.getText();
		            String pname = txtPname.getText();
		            String edition = txtEdition.getText();
		            String price = txtPrice.getText();
		            String quantity = txtQuantity.getText();
		           
		            String status = txtStatus.getSelectedItem().toString();
		            String quality = txtQuality.getSelectedItem().toString();
		            
		            // Lấy ngày từ JDateChooser
                    java.util.Date utilDate = txtDate.getDate();
                    java.sql.Date date = new java.sql.Date(utilDate.getTime());
                    
                    

                    // Kiểm tra xem có trường nào trống không
                    if (pid.isEmpty() || pname.isEmpty() || edition.isEmpty() || price.isEmpty() || quantity.isEmpty() || date == null || status.isEmpty() || quality.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return; // Không thực hiện thêm vào bảng nếu có trường trống
                    }

		            // Chuyển đổi các giá trị cần thiết thành kiểu phù hợp
		            int intPrice = Integer.parseInt(price);
		            int intQuantity = Integer.parseInt(quantity);

		            // Thêm dữ liệu vào bảng (giả sử `table` là đối tượng JTable của bạn)
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            model.addRow(new Object[]{pid, pname, edition, intPrice, intQuantity, date, status, quality});

		            // Xóa dữ liệu từ các trường nhập liệu sau khi thêm
		            txtPID.setText(null);
		            txtPname.setText(null);
		            txtEdition.setText(null);
		            txtPrice.setText(null);
		            txtQuantity.setText(null);
		            txtDate.setDate(null);
		            txtStatus.setSelectedItem(model);
		            txtQuality.setSelectedItem(model);

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(frame, "Vui lòng nhập giá và số lượng là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
			
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
	}


private void findProductByID() {
    try {
        String id = txtPID.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Vui lòng nhập ID để tìm kiếm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean found = false;
        for (int i = 0; i < table.getRowCount(); i++) {
            String tableID = table.getValueAt(i, 0).toString();
            if (id.equals(tableID)) {
                table.setRowSelectionInterval(i, i);
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(frame, "Không tìm thấy sản phẩm với ID đã nhập.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
}

