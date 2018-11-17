package guru.springframework.sfgpetclinic;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

/**
 * Created by jt on 2018-10-28.
 */
public class CustomArgsProvider implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
		return Stream.of(Arguments.of("FL", 7, 10), Arguments.of("OH", 11, 42), Arguments.of("MI", 44, 77));
	}
}
