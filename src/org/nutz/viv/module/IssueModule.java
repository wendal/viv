package org.nutz.viv.module;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import lombok.Setter;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.adaptor.VoidAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.viv.bean.AttachmentBean;
import org.nutz.viv.bean.CommentBean;
import org.nutz.viv.bean.IssueBean;
import org.nutz.viv.bean.UserBean;
import org.nutz.viv.dao.IssueDao;

import com.bugull.mongo.fs.BuguFS;
import com.mongodb.gridfs.GridFSDBFile;

@IocBean
@At("/issue")
@AdaptBy(type=JsonAdaptor.class)
public class IssueModule {

	@At
	public void create(@Param("title")String title, @Param("content")String content, @Attr("me") UserBean me){
		IssueBean issue = IssueBean.create(title, content, me);
		long maxIssueNo = (long) issueDao.max("issueNo");
		issue.setIssueNo(maxIssueNo + 1);
		issueDao.insert(issue);
	}

	@At
	public Object query() { //TODO 支持自定义查询
		return issueDao.findAll();
	}

	@At("/?/comment/add")
	public void addComment(long issueNo, @Param("content")String content, @Attr("me") UserBean me) {
		CommentBean comment = CommentBean.create(me, content);
		issueDao.push(_getIssueBy(issueNo), "comments", comment);
	}

	@At("/?/close")
	public void closeIssue(long issueNo) {
		issueDao.set(_getIssueBy(issueNo), "status", "CLOSE");
	}

	@At("/?/reopen")
	public void reopenIssue(long issueNo) {
		issueDao.set(_getIssueBy(issueNo), "status", "OPEN");
	}

	@At("/?/tag/add")
	public void addTags(long issueNo, @Param("tag")String tag) {
		issueDao.push(_getIssueBy(issueNo), "tags", tag);
	}

	@At("/?/tag/remove")
	public void removeTags(long issueNo, @Param("tag")String tag) {
		issueDao.pull(_getIssueBy(issueNo), "tags", tag);
	}

	@At("/?/watcher/add")
	public void addWatcher(long issueNo, @Attr("me") UserBean me) {
		issueDao.push(_getIssueBy(issueNo), "watchers", me);
	}

	@At("/?/watcher/remove")
	public void removeWatcher(long issueNo, @Attr("me") UserBean me) {
		issueDao.pull(_getIssueBy(issueNo), "watchers", me);
	}

	@AdaptBy(type=VoidAdaptor.class)
	@At("/?/attachment/add/?")
	public void addAttachment(long issueNo, String filename, @Attr("me") UserBean me) throws IOException {
		BuguFS.getInstance().save(Mvcs.getReq().getInputStream(), filename, issueNo + "/attachment/"); //TODO 支持中文名
		GridFSDBFile file = BuguFS.getInstance().findOne(issueNo + "/attachment/" + filename);
		AttachmentBean attachment = AttachmentBean.create(me, filename, file.getLength());
		issueDao.push(_getIssueBy(issueNo), "attachments", attachment);
	}
	
	@At("/?/attachment/download/?")
	@Ok("raw")
	@Fail("http:404")
	public InputStream downloadAttachment(long issueNo, String filename, HttpServletResponse resp) throws UnsupportedEncodingException {
		resp.setHeader("Content-Disposition", "attachment; filename=" + filename);
		GridFSDBFile file = BuguFS.getInstance().findOne(issueNo + "/attachment/" + filename);
		resp.setHeader("Content-Length", "" + file.getLength());
		return file.getInputStream();
	}
	
	@Setter
	@Inject
	private IssueDao issueDao;
	
	protected IssueBean _getIssueBy(long issueNo) {
		return (IssueBean) issueDao.findOne("issueNo", issueNo);
	}
}
