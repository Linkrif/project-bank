package br.com.projectbank

import br.com.projectbank.seeder.DefaultSeeder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import javax.annotation.PostConstruct


class ServletInitializer : SpringBootServletInitializer() {

	@Autowired
	lateinit var defaultSeeder: DefaultSeeder

	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(ProjectBankApplication::class.java)
	}

	@PostConstruct
	fun onStart() {
		defaultSeeder.seedAdmin()
	}

}
