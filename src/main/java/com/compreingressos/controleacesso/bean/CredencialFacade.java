/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.compreingressos.controleacesso.Contratante;
import com.compreingressos.controleacesso.Credenciado;
import com.compreingressos.controleacesso.Credencial;
import com.compreingressos.controleacesso.Municipio;
import com.compreingressos.controleacesso.TipoCredencial;

/**
 *
 * @author Intuiti 04
 */
@Stateless
public class CredencialFacade extends AbstractFacade<Credencial> {

	@PersistenceContext(unitName = "com.compreingressos_controleacesso_war_1.0.0PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CredencialFacade() {
		super(Credencial.class);
	}

	public Credencial update(Credencial entity) {
		return (Credencial) getEntityManager().merge(entity);
	}

	@Override
	public List<Credencial> findAll() {
		String q = "SELECT c.codigo as codigoCredenciais, co.nome, co.sobrenome, co.cpf, co.rg, "
				+ "c.numeroTag, c.validade, tc.descricao, co.endereco, co.numeroEndereco, "
				+ "co.bairro, co.complementoEndereco, co.cep, tc.codigo as codigoTipoCredencial, co.telefone2, "
				+ "co.email, co.nomeImpressao, co.observacao, co.foto, co.telefone,"
				+ "co.codigo as codigoCredenciado, con.codigo as codigoContratante, m.codigo as codigoMunicipio, co.ativo "
				+ "FROM Credencial c "
				+ "RIGHT OUTER JOIN Credenciado co ON c.credenciado = co.codigo "
				+ "LEFT JOIN TipoCredencial tc ON tc.codigo = c.tipoCredencial "
				+ "LEFT JOIN Contratante con ON con.codigo = co.empresa "
				+ "LEFT JOIN Municipio m ON m.codigo = co.municipio";
		List<Object[]> lista = getEntityManager().createNativeQuery(q).getResultList();
		List<Credencial> credenciais = new ArrayList<>();
		for (Object[] o : lista) {
			Credenciado credenciado = new Credenciado();
			credenciado.setNome(o[1].toString());
			credenciado.setSobrenome(o[2].toString());
			credenciado.setCpf(o[3].toString());
			credenciado.setRg(o[4].toString());
			credenciado.setEndereco(o[8].toString());
			credenciado.setNumeroEndereco(o[9].toString());
			credenciado.setBairro(o[10].toString());
			credenciado.setComplementoEndereco(o[11].toString());
			credenciado.setCep(o[12].toString());
			credenciado.setTelefone(o[19].toString());
			credenciado.setTelefone2(o[14].toString());
			credenciado.setEmail(o[15].toString());
			credenciado.setNomeImpressao(o[16].toString());
			credenciado.setObservacao(o[17].toString());
			credenciado.setFoto(o[18].toString());
			credenciado.setCodigo((Integer) o[20]);
			credenciado.setEmpresa(new Contratante((Integer) o[21]));
			credenciado.setMunicipio(new Municipio((Integer) o[22]));
			credenciado.setAtivo((boolean)o[23]);
			
			Credencial credencial = new Credencial();
			credencial.setNumeroTag(o[5] == null ? null : o[5].toString());
			credencial.setValidade(o[6] == null ? null : (Date) o[6]);
			credencial.setCodigo(o[0] == null ? 0 : (Integer) o[0]);
			credencial.setCredenciado(credenciado);

			TipoCredencial tipoCredencial = new TipoCredencial();
			tipoCredencial.setCodigo(o[13] == null ? null : (Integer)o[13]);
			tipoCredencial.setDescricao(o[7] == null ? null : o[7].toString());
			credencial.setTipoCredencial(tipoCredencial);
			credenciais.add(credencial);
		}
		return credenciais;
	}

}
