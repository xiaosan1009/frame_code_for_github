package com.richClientFrame.util;

import com.richClientFrame.exception.RichClientWebException;


/**
 * 生成分页信息类
 * @author King
 * @since	2010.11.07
 */
public class PageCreate {

	/**
	 * 数据总数
	 */
	private int totalRows = 0;

	/**
	 * 一页数据数量
	 */
	private int pageRecorders = 20;

	/**
	 * 总页数
	 */
	private int totalPages = 0;

	/**
	 * 当前页数
	 */
	private int currentPage = 1;

	/**
	 * 是否有下一页
	 */
	private boolean hasNextPage = false;

	/**
	 * 是否有上一页
	 */
	private boolean hasPreviousPage = false;

	/**
	 * <p>
	 * 取得数据总数
	 * </p>
	 * 
	 * @return 数据总数
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * <p>
	 * 设置数据总数
	 * </p>
	 * 
	 * @param totalRows 数据总数
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * <p>
	 * 取得一页数据数
	 * </p>
	 * 
	 * @return 一页数据数
	 */
	public int getPageRecorders() {
		return pageRecorders;
	}

	/**
	 * <p>
	 * 设置一页数据数
	 * </p>
	 * 
	 * @param pageRecorders 一页数据数
	 */
	public void setPageRecorders(int pageRecorders) {
		this.pageRecorders = pageRecorders;
	}

	/**
	 * <p>
	 * 取得页面总数
	 * </p>
	 * 
	 * @return 页面总数
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * <p>
	 * 设置页面总数
	 * </p>
	 * 
	 * @param totalPages 页面总数
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * <p>
	 * 取得当前页数
	 * </p>
	 * 
	 * @return 当前页数
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * <p>
	 * 设置当前页数
	 * </p>
	 * 
	 * @param currentPage 当前页数
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * <p>
	 * 是否有下一页
	 * </p>
	 * 
	 * @return 是否有下一页
	 */
	public boolean isHasNextPage() {
		return hasNextPage;
	}

	/**
	 * <p>
	 * 设置是否有下一页
	 * </p>
	 * 
	 * @param hasNextPage 是否有下一页
	 */
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * <p>
	 * 是否有上一页
	 * </p>
	 * 
	 * @return 是否有上一页
	 */
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	/**
	 * <p>
	 * 设置是否有上一页
	 * </p>
	 * 
	 * @param hasPreviousPage 是否有上一页
	 */
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	/**
	 * 分页信息初期化.
	 * @param totalRows 数据总数
	 * @param pageRecorders 每页显示数据数
	 * @param currentPage 当前页数
	 * @throws RichClientWebException RichClientWebException
	 */
	public PageCreate(int totalRows, int pageRecorders, int currentPage) 
	    throws RichClientWebException {
	    
	    if (pageRecorders == 0) {
	        return;
	    }

		this.pageRecorders = pageRecorders;
		this.currentPage = currentPage;
		
		// 取得数据总数
		this.totalRows = totalRows;

		// 取得总页数
		if ((totalRows % pageRecorders) == 0) {
			totalPages = totalRows / pageRecorders;
		} else {
			totalPages = totalRows / pageRecorders + 1;
		}
		totalPages = (totalPages == 0) ? 1 : totalPages;
		
		if (currentPage <= 0) {
			currentPage = 1;
		}

		// 判断是否有下一页
		if (currentPage >= totalPages) {
			this.currentPage = totalPages;
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		
		// 判断是否有上一页
		if (currentPage > 1) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
	}
}
