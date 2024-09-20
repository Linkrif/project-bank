package br.com.projectbank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProjectBankApplication

{

}



fun main(args: Array<String>) {
	runApplication<ProjectBankApplication>(*args)
}