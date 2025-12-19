package krasa.renderdoc.integration;

import com.intellij.execution.actions.ConsoleActionsPostProcessor;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.diagnostic.Logger;
import krasa.renderdoc.action.StartRenderDocConsoleAction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class RenderDocConsoleActionsPostProcessor extends ConsoleActionsPostProcessor {
	private static final Logger log = Logger.getInstance(RenderDocConsoleActionsPostProcessor.class.getName());

	@NotNull
	@Override
	public AnAction[] postProcess(@NotNull ConsoleView console, @NotNull AnAction[] actions) {
		RenderDocContext context = RenderDocContext.load();
		ArrayList<AnAction> anActions = new ArrayList<AnAction>();
		anActions.add(new StartRenderDocConsoleAction(context));
		anActions.addAll(Arrays.asList(actions));
		return anActions.toArray(new AnAction[anActions.size()]);
	}
}
