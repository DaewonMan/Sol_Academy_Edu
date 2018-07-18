package com.dwm.apr16_1_pw.post;

import java.util.List;

public interface PostMapper {
	public abstract int addPost(Post p);
	public abstract List<Post> searchPost(Post p);
	public abstract List<Post> searchPostByNo2(Post p);
	public abstract Post searchPostByNo(Post p);
	public abstract Post searchPostByNo3(Post p);
	public abstract int insertReple(Reple r);
	public abstract List<Reple> searchRepleByRNo(Reple r);
	public abstract int deleteRepleByNo(Reple r);
	public abstract int deleteRepleByRno(Reple r);
	public abstract int deletePostByNo(Post p);
	public abstract int updateLikeByNo(Post p);
	public abstract Reple searchRepleByRNo2(Reple r);
	
	public abstract int insertLikeBoolById(LikeBool lb);
	public abstract int updateLikeBoolByIdPno(LikeBool lb);
	public abstract int deleteLikeBoolByPno(LikeBool lb);
	public abstract List<LikeBool> searchLikeBoolByIdPno(LikeBool lb);
}
