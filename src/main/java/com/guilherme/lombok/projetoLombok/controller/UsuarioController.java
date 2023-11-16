package com.guilherme.lombok.projetoLombok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.lombok.projetoLombok.entities.Usuario;
import com.guilherme.lombok.projetoLombok.services.UsuarioServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name="Usuarios", description= "API rest de gerenciamento de usuarios")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	private final UsuarioServices usuarioServices;

	@Autowired
	public UsuarioController(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}

	@GetMapping("/{id}")
	@Operation(summary= "Localiza usuario por id")
	public ResponseEntity<Usuario> findUsuariobyId(@PathVariable Long id) {
		Usuario usuario = usuarioServices.findUsuarioById(id);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary= "Apresenta todos os usuarios")
	public ResponseEntity<List<Usuario>> findAllUsuarioscontrol() {
		List<Usuario> usuarios = usuarioServices.findAllUsuario();
		return ResponseEntity.ok(usuarios);
	}

	@PostMapping
	@Operation(summary= "cadastra um usuario")
	public ResponseEntity<Usuario> insertUsuariosControl(@RequestBody @Valid Usuario usuario) {
		Usuario novoUsuario = usuarioServices.insertUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
	}

	@PutMapping("/id")
	@Operation(summary= "Altera um usuario")
	public ResponseEntity<Usuario> updateUsuarioControl(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
		Usuario mudausuario = usuarioServices.updateUsuario(id, usuario);
		if (mudausuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/id")
	@Operation(summary= "Exclui um usuario")
	public ResponseEntity<String> deleteUsuarioControl(@PathVariable Long id) {
		boolean remover = usuarioServices.deleteUsuario(id);
		if (remover) {
			return ResponseEntity.ok().body("usuario Excluido com sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}
