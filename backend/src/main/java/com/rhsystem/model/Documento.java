package com.rhsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "documentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Funcionário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @NotBlank(message = "Nome do documento é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "Tipo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipo;

    @NotBlank(message = "URL do arquivo é obrigatória")
    @Column(name = "arquivo_url", nullable = false)
    private String arquivoUrl;

    @Column(name = "data_upload", nullable = false)
    private LocalDateTime dataUpload = LocalDateTime.now();

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Column(name = "tamanho_bytes")
    private Long tamanhoBytes;

    @Column(name = "tipo_mime")
    private String tipoMime;

    @Column(length = 500)
    private String descricao;

    @Column(name = "upload_por_id")
    private Long uploadPorId;

    @Column(nullable = false)
    private Boolean ativo = true;
}
