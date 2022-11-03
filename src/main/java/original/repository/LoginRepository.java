package original.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import original.model.Login;

public interface LoginRepository extends JpaRepository<Login,Long>{

}
