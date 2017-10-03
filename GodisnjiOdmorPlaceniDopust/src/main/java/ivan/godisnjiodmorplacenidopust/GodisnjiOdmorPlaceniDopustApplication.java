package ivan.godisnjiodmorplacenidopust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import controller.HomeControler;

@SpringBootApplication
@ComponentScan(basePackageClasses = HomeControler.class)
public class GodisnjiOdmorPlaceniDopustApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(GodisnjiOdmorPlaceniDopustApplication.class, args);
	}
}
