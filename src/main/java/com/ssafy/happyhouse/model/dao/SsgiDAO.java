package com.ssafy.happyhouse.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.Ssgi;
import com.ssafy.happyhouse.util.DBUtil;

@Repository
public class SsgiDAO {

    public List<Ssgi> selectSsgi(Long dongCode) throws SQLException  {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT 상호명, 상권업종소분류명,법정동코드,지번주소  FROM ssgi WHERE 법정동코드 = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, dongCode);
            System.out.println(dongCode);
            List<Ssgi> list = new ArrayList<Ssgi>();
            
            rs = stmt.executeQuery();
            while (rs.next()) {
            	Ssgi ssgi = new Ssgi(rs.getString(1),rs.getString(2),dongCode,rs.getString(4));
                list.add(ssgi);
                System.out.println(ssgi);
            }
            return list;
            
        }  finally {
            DBUtil.close(conn, stmt);
            DBUtil.close(rs);
        }
    }
}
