package com.mvc.spring.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.javafaker.Faker;
import com.mvc.spring.model.Cargo;
import com.mvc.spring.model.Equipo;



@Service
public class EquipoServiceImpl implements EquipoService{
	
	private final Logger log = LoggerFactory.getLogger(EquipoServiceImpl.class);
	
	@Override
	public Collection<Equipo> getEquipo() {
		log.info("------------------------------Rest Template getEquipo");
		RestTemplate restTemplate = new RestTemplate();
		    Equipo[] equipos = restTemplate.getForObject("http://localhost:5000/equipo", Equipo[].class);
		    List<Equipo> listaEquipos = Arrays.asList(equipos);
		    System.out.println(listaEquipos);
		    return listaEquipos;
	}
	
	@Override
	public void addEquipo(Equipo equipo) {
		log.info("------------------------------Rest Template addEquipo");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject("http://localhost:5000/equipo/admin/post", equipo, Equipo.class);
	}
	
	@Override
	public Equipo selectEquipo(Integer id) {
		log.info("------------------------------Rest Template selectEquipo" + id);
		RestTemplate restTemplate = new RestTemplate();
		Equipo equipo = restTemplate.getForObject("http://localhost:5000/equipo/admin/select/"+id, Equipo.class);
	    log.info(""+equipo);
	    return equipo;
	}
	
	@Override
	public void updateEquipo(Equipo equipo) {
		log.info("------------------------------Rest Template updateEquipo");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put("http://localhost:5000/equipo/admin/update/"+equipo.getIdpersona(), equipo, Equipo.class);
	}
	
	@Override
	public void deleteEquipo(Integer id) {
		log.info("------------------------------Rest Template deleteEquipo");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:5000/equipo/admin/delete/"+id, Equipo.class);
		
	}
	
	
	public Equipo newFakeMember () {
		Equipo e = new Equipo();
		Cargo c = new Cargo();
		Faker faker = new Faker();
		c.setCargo(faker.name().title());
		e.setNombre(faker.name().firstName());
		e.setApellidos(faker.name().lastName());
		e.setFoto("https://100k-faces.glitch.me/random-image");
		e.setResumen(faker.hobbit().quote());
		e.setCargo(c);
		
		return e;
	}
	
	
	
}