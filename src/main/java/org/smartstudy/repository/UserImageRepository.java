package org.smartstudy.repository;

import java.util.List;

import org.smartstudy.model.UserFiles;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends CrudRepository<UserFiles,Long>{

	@Query("Select f from UserFiles as f where f.user.id = ?1")
	List<UserFiles> findFilesByUserId(Long userId);

	@Modifying
	@Query("delete from UserFiles as f where f.user.id = ?1 and f.modifiedFileName in (?2) " )
	void deleteFilesByUserIdAndImageNames(Long id, List<String> removeImages);
	@Modifying
	@Query("delete from UserFiles as f where f.user.id = ?1 "  )
	void deleteFilesByUserId(Long userId);
	
}
