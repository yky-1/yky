package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.Author;

public class AuthorDao {
	
	public String getUserType(String username){
		String sql="select type from user where name='"+username+"'";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				return dao.getString("type");
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Author> getAll()throws SQLException,Exception{
		List<Author> authorities=new ArrayList<Author>();
		databaseDao databaseDao=new databaseDao();
		databaseDao.query("select * from authority");
		while (databaseDao.next()) {
			Author author=new Author();
			author.setAuthorityId(databaseDao.getInteger("authorityId"));
			author.setUserType(databaseDao.getString("userType"));
			author.setUrl(databaseDao.getString("url"));
			author.setRedirectUrl(databaseDao.getString("redirectUrl"));
			author.setParam(databaseDao.getString("param"));
			authorities.add(author);
		}
		return authorities;
	}
}
