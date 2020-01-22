package upload;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.opencsv.CSVReader;

//csv 파일의 내용을 테이블에 저장(mysql)
public class importCsv {

	public static void main(String[] args) {
		try (InputStreamReader is = new InputStreamReader(new FileInputStream("data/project_code.csv"), "EUC-KR");
				CSVReader reader = new CSVReader(is, ',', '"', 1);
				Connection connection = dbConnection.getConnection();) {
			String insertQuery = "Insert into project_code (project_code, project_gubun, project_name, x_location, y_location) values (?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(insertQuery);
			String[] rowData = null;
			int i = 0;
			while ((rowData = reader.readNext()) != null) {
				for (String data : rowData) {
					pstmt.setString((i % 5) + 1, data);

					if (++i % 5 == 0)
						pstmt.addBatch();// add batch

					if (i % 50 == 0)// insert when the batch size is 10
						pstmt.executeBatch();
				}
			}
			System.out.println("Data Successfully Uploaded");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
