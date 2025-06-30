package com.example.pfev2.controllers;

import com.example.pfev2.entites.Coproprietaire;
import com.example.pfev2.entites.Prestataire;
import com.example.pfev2.entites.Syndic;
import com.example.pfev2.entites.Utilisateur;
import com.example.pfev2.repositorys.CoproprietaireRepository;
import com.example.pfev2.repositorys.PrestataireRepository;
import com.example.pfev2.repositorys.SyndicRepository;
import com.example.pfev2.repositorys.UtilisateurRepository;
import com.example.pfev2.services.JwtService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final PrestataireRepository prestataireRepository;
    private final CoproprietaireRepository coproprietaireRepository;
    private final SyndicRepository syndicRepository;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(utilisateur);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(jwtToken)
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        var utilisateur = Utilisateur.builder()
                .email(request.getEmail()).num(request.getNum())
                .mdpHash(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

if (request.getRole().toString()=="COPROPRIETAIRE") {
    System.out.println(utilisateur);
   Coproprietaire C =new Coproprietaire();
   C.setEmail(utilisateur.getEmail());
   C.setRole(utilisateur.getRole());
   C.setMdpHash(utilisateur.getMdpHash());
   C.setNum(utilisateur.getNum());
   C.setSoldeCharges(request.getSoldeCharges());
    coproprietaireRepository.save(C);

}
else if(request.getRole().toString()==("SYNDIC")) {
   Syndic C =new Syndic();
    C.setEmail(utilisateur.getEmail());
    C.setRole(utilisateur.getRole());
    C.setMdpHash(utilisateur.getMdpHash());
    C.setNum(utilisateur.getNum());
    syndicRepository.save(C);
}
else if ("PRESTATAIRE".equals(request.getRole().toString())) {
    Prestataire C = new Prestataire();
    C.setEmail(utilisateur.getEmail());
    C.setRole(utilisateur.getRole());
    C.setMdpHash(utilisateur.getMdpHash());
    C.setNum(utilisateur.getNum());
    C.setSpecialite(request.getSpecialite());
    prestataireRepository.save(C);


}

        var jwtToken = jwtService.generateToken(utilisateur);
        return ResponseEntity.ok(AuthResponse.builder()
                .token(jwtToken)
                .build());
    }
}

// DTOs
@Data
@Builder
class AuthRequest {
    private String email;
    private String password;
}

@Data
@Builder
class AuthResponse {
    private String token;
}

@Data
@Builder
class RegisterRequest {
    private String email;
    private String password;
    private  String num ;
     private Float soldeCharges;
    private Utilisateur.Role role;
    private String specialite;

}