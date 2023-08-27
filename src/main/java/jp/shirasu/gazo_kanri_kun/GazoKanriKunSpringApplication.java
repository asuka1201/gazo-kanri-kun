package jp.shirasu.gazo_kanri_kun;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javafx.application.Application;
import javafx.scene.Node;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.InjectionPointLazyFxControllerAndViewResolver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;

/**
 * がぞうかんり君
 * 起動用Springアプリケーション
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class GazoKanriKunSpringApplication {

	public static void main(String[] args) {
		Application.launch(GazoKanriKunFXMLApplication.class, args);
	}

	@Bean
	FxWeaver fxWerver(ConfigurableApplicationContext applicationContext) {
		return new SpringFxWeaver(applicationContext);
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	<C, V extends Node> FxControllerAndView<C, V> controllerAndView(FxWeaver fxWeaver, InjectionPoint injectionPoint) {
		return new InjectionPointLazyFxControllerAndViewResolver(fxWeaver).resolve(injectionPoint);
	}
}