package com.projectfinal.entitycrud.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projectfinal.entitycrud.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}


