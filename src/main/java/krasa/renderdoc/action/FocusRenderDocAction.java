package krasa.renderdoc.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.NlsActions;
import krasa.renderdoc.integration.RenderDocHelper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class FocusRenderDocAction extends MyDumbAwareAction {
	public FocusRenderDocAction() {
	}

	public FocusRenderDocAction(@Nullable @NlsActions.ActionText String text, @Nullable @NlsActions.ActionDescription String description, @Nullable Icon icon) {
		super(text, description, icon);
	}

	public void actionPerformed(AnActionEvent e) {
		RenderDocHelper.executeRenderDoc(e.getProject(),  "--window-to-front");
	}

}
