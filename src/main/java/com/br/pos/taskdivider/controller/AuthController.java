package com.br.pos.taskdivider.controller;

import com.br.pos.taskdivider.controller.request.LoginRequest;
import com.br.pos.taskdivider.controller.response.JwtResponse;
import com.br.pos.taskdivider.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		JwtResponse jwtResponse = usuarioService.autenticaUsuario(loginRequest.getNome(),
				loginRequest.getSenha());

		return ResponseEntity.ok(jwtResponse);
	}
	
}
