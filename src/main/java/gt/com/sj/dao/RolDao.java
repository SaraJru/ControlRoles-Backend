package gt.com.sj.dao;

import gt.com.sj.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository <Roles, Integer> {
    
}
