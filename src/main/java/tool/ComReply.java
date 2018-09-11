package tool;

import java.util.List;

import javabean.Comment;
import javabean.Reply;

public class ComReply {
	private Comment comment;
	private List<Reply> reply;
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public List<Reply> getReply() {
		return reply;
	}
	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}
}
