package com.HTT.company.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.HTT.company.entity.Users;

@Transactional
@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
	
	@Query(value = "SELECT * FROM Users", nativeQuery=true)
	List<Users> findAllUsers();
	
	@Query(value = "SELECT * FROM Users WHERE user_name = :username", nativeQuery=true)
	Users findByUsersName(@Param("username") String username);
	
	@Query(value = "SELECT * FROM Users WHERE gmail = :gmail", nativeQuery=true)
	Users findByGmail(@Param("gmail") String gmail);
	
	@Query(value = "SELECT * FROM Users WHERE gmail = :gmail", nativeQuery=true)
	Users findByGmailAddress(@Param("gmail") String gmail);
	
	
	@Modifying
	@Query(value = 
		  "INSERT INTO Users (user_name, pass_word, account_name, number_phone, gmail, avatar)"
		+ "VALUES "
		+ "(:#{#entity.username}, :#{#entity.password}, :#{#entity.accountName},:#{#entity.numberPhone}, :#{#entity.gmail}, :#{#entity.avatar})" ,nativeQuery=true)
	Integer createUsers(@Param("entity") Users user);
	
	@Modifying
	@Query(value = 
		  "UPDATE Users "
		+ "SET account_name = :#{#entity.account_name}, account_balance = :#{#entity.account_balance}, number_phone = :#{#entity.number_phone}, "
		+ "avatar =  :#{#entity.avatar}, dataupdate = curdate() "
		+ "WHERE user_name = :#{#entity.user_name}" ,nativeQuery=true)
	Integer updateUsers(@Param("entity") Users user);
	
	@Modifying
	@Query(value = 
		  "UPDATE Users "
		+ "SET is_exist = 0 WHERE account_name = :account_name", nativeQuery=true)
	Integer deleteUsers(@Param("account_name") String codeDelete);
	
}
