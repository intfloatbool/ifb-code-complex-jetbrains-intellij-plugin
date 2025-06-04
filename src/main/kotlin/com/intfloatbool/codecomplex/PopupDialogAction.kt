package com.intfloatbool.codecomplex

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class PopupDialogAction : AnAction() {

    override fun update(e: AnActionEvent) {
        // using the event, evaluate the context
        // enable or disable the action
    }

    override fun actionPerformed(e: AnActionEvent) {
        // using the event, implement an action
        // for example: create and show dialog
        Messages.showMessageDialog(e.project, "Hey boy from plugin!", "Plugin Info", Messages.getInformationIcon())
    }

    // for UI - EDT (ui thread) , for Others - BGT (background threads)
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.EDT
    }

}