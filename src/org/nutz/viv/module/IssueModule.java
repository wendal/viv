package org.nutz.viv.module;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

@IocBean
@At("/issue")
public class IssueModule {

	@At
	public void create(){}

	@At
	public void query() {}

	@At("/comment/add")
	public void addComment() {}

	@At("/close")
	public void closeIssue() {}

	@At("/reopen")
	public void reopenIssue() {}

	@At("/tag/add")
	public void addTags() {}

	@At("/tag/remove")
	public void removeTags() {}

	@At("/watcher/add")
	public void addWatcher() {}

	@At("/watcher/remove")
	public void removeWatcher() {}

	@At("/attachment")
	public void addAttachment() {}
}
