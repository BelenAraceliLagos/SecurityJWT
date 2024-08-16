package cl.praxis.SecurityJWT;

import cl.praxis.SecurityJWT.persistence.entity.PermissionEntity;
import cl.praxis.SecurityJWT.persistence.entity.RoleEntity;
import cl.praxis.SecurityJWT.persistence.entity.RoleEnum;
import cl.praxis.SecurityJWT.persistence.entity.UserEntity;
import cl.praxis.SecurityJWT.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SecurityJwtApplication {

	public static void main(String[] args) {

		SpringApplication.run(SecurityJwtApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			PermissionEntity createPermission = new PermissionEntity().builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = new PermissionEntity().builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = new PermissionEntity().builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = new PermissionEntity().builder()
					.name("DELETE")
					.build();

			RoleEntity roleAdmin = new RoleEntity().builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = new RoleEntity().builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = new RoleEntity().builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();

			RoleEntity developerAdmin = new RoleEntity().builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			UserEntity user1 = UserEntity.builder()
					.username("user1")
					.password("$2a$10$uvXmv2fvzMm2jw5cNBBG8upvQOy5Oe9YuRx6VEc1ZHd9RSf6QPYrm")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity user2 = UserEntity.builder()
					.username("user2")
					.password("$2a$10$uvXmv2fvzMm2jw5cNBBG8upvQOy5Oe9YuRx6VEc1ZHd9RSf6QPYrm")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity user3 = UserEntity.builder()
					.username("user3")
					.password("$2a$10$uvXmv2fvzMm2jw5cNBBG8upvQOy5Oe9YuRx6VEc1ZHd9RSf6QPYrm")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(developerAdmin))
					.build();

			userRepository.saveAll(List.of(user1, user2, user3));
		};
	}

}
