package krasa.renderdoc.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import krasa.renderdoc.ApplicationSettingsService;
import krasa.renderdoc.LogHelper;
import krasa.renderdoc.MyConfigurable;
import krasa.renderdoc.Resources;
import krasa.renderdoc.integration.RenderDocContext;
import krasa.renderdoc.integration.RenderDocHelper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class StartRenderDocConsoleAction extends MyDumbAwareAction {
	private RenderDocContext visualVMContext;
	private boolean postConstructContextSet;
	private long created;

	public static volatile List<StartRenderDocConsoleAction> currentlyExecuted = new LinkedList<StartRenderDocConsoleAction>();

	public StartRenderDocConsoleAction() {
	}

	public StartRenderDocConsoleAction(RenderDocContext visualVMContext) {
		super("Start RenderDoc", null, Resources.CONSOLE_RUN);
		this.visualVMContext = visualVMContext;
		created = System.currentTimeMillis();
		currentlyExecuted.add(this);
		LogHelper.print("created with " + visualVMContext, this);
	}

	@Override
	public void update(AnActionEvent e) {
		super.update(e);
		final Presentation presentation = e.getPresentation();
		if (!RenderDocContext.isValid(visualVMContext)) {
//			presentation.setVisible(false);
			presentation.setEnabled(false);
		} else {
			presentation.setDescription("Open RenderDoc with id=" + visualVMContext.getAppId());
		}
	}

	@Override
	public void actionPerformed(final AnActionEvent e) {
		if (!MyConfigurable.openSettingsIfNotConfigured(e.getProject())) {
			return;
		}
		RenderDocHelper.startRenderDoc(visualVMContext, e.getProject(), this);
	}

	public void setRenderDocContext(RenderDocContext visualVMContext) {
		if (postConstructContextSet) {
			LogHelper.print("setRenderDocContext false with " + visualVMContext, this);
		} else {
			postConstructContextSet = true;
			LogHelper.print("setRenderDocContext " + visualVMContext, this);
			this.visualVMContext = visualVMContext;
		}
	}

	public long getCreated() {
		return created;
	}

	public static void setRenderDocContextToRecentlyCreated(RenderDocContext visualVMContext) {
		LogHelper.print("#setRenderDocContextToRecentlyCreated" + visualVMContext, null);
		Iterator<StartRenderDocConsoleAction> iterator = currentlyExecuted.iterator();
		while (iterator.hasNext()) {
			StartRenderDocConsoleAction next = iterator.next();
			if (isRecentlyCreated(next)) {
				next.setRenderDocContext(visualVMContext);
			} else {
				LogHelper.print("#setRenderDocContextToRecentlyCreated remove", null);
				iterator.remove();
			}
		}
	}

	private static boolean isRecentlyCreated(StartRenderDocConsoleAction next) {
		long l = System.currentTimeMillis() - next.getCreated();
		LogHelper.print("#isRecentlyCreated " + l + " " + next, null);
		return l < ApplicationSettingsService.getInstance().getState().getDurationToSetContextToButtonAsLong();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("StartRenderDocConsoleAction");
		sb.append("{visualVMContext=").append(visualVMContext);
		sb.append(", created=").append(created);
		sb.append('}');
		return sb.toString();
	}
}
