package com.snippetUimanagement.snippet.updateSnippet

import com.snippetUimanagement.classes.Snippet
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.UUID

class UpdateSnippet {
    companion object {
        fun updateSnippetCode(snippetById: UUID, code: String, token:String) : Snippet?{
            val canUpdate = SnippetManageRepositories.checkIfICanUpdate(token, snippetById)
            if(canUpdate){
                return SnippetManagmentServiceSnippet.updateSnippet(snippetById, code, token)
            }else{
                return null
            }
            /*//TODO
            SnippetTestingScripts.runTestsBySnippetUuid(snippetById)*/
        }
    }


}