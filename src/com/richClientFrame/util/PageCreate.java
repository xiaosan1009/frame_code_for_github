package com.richClientFrame.util;

import com.richClientFrame.exception.RichClientWebException;


/**
 * ���ɷ�ҳ��Ϣ��
 * @author King
 * @since	2010.11.07
 */
public class PageCreate {

	/**
	 * ��������
	 */
	private int totalRows = 0;

	/**
	 * һҳ��������
	 */
	private int pageRecorders = 20;

	/**
	 * ��ҳ��
	 */
	private int totalPages = 0;

	/**
	 * ��ǰҳ��
	 */
	private int currentPage = 1;

	/**
	 * �Ƿ�����һҳ
	 */
	private boolean hasNextPage = false;

	/**
	 * �Ƿ�����һҳ
	 */
	private boolean hasPreviousPage = false;

	/**
	 * <p>
	 * ȡ����������
	 * </p>
	 * 
	 * @return ��������
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * <p>
	 * ������������
	 * </p>
	 * 
	 * @param totalRows ��������
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * <p>
	 * ȡ��һҳ������
	 * </p>
	 * 
	 * @return һҳ������
	 */
	public int getPageRecorders() {
		return pageRecorders;
	}

	/**
	 * <p>
	 * ����һҳ������
	 * </p>
	 * 
	 * @param pageRecorders һҳ������
	 */
	public void setPageRecorders(int pageRecorders) {
		this.pageRecorders = pageRecorders;
	}

	/**
	 * <p>
	 * ȡ��ҳ������
	 * </p>
	 * 
	 * @return ҳ������
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * <p>
	 * ����ҳ������
	 * </p>
	 * 
	 * @param totalPages ҳ������
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * <p>
	 * ȡ�õ�ǰҳ��
	 * </p>
	 * 
	 * @return ��ǰҳ��
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * <p>
	 * ���õ�ǰҳ��
	 * </p>
	 * 
	 * @param currentPage ��ǰҳ��
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * <p>
	 * �Ƿ�����һҳ
	 * </p>
	 * 
	 * @return �Ƿ�����һҳ
	 */
	public boolean isHasNextPage() {
		return hasNextPage;
	}

	/**
	 * <p>
	 * �����Ƿ�����һҳ
	 * </p>
	 * 
	 * @param hasNextPage �Ƿ�����һҳ
	 */
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * <p>
	 * �Ƿ�����һҳ
	 * </p>
	 * 
	 * @return �Ƿ�����һҳ
	 */
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	/**
	 * <p>
	 * �����Ƿ�����һҳ
	 * </p>
	 * 
	 * @param hasPreviousPage �Ƿ�����һҳ
	 */
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	/**
	 * ��ҳ��Ϣ���ڻ�.
	 * @param totalRows ��������
	 * @param pageRecorders ÿҳ��ʾ������
	 * @param currentPage ��ǰҳ��
	 * @throws RichClientWebException RichClientWebException
	 */
	public PageCreate(int totalRows, int pageRecorders, int currentPage) 
	    throws RichClientWebException {
	    
	    if (pageRecorders == 0) {
	        return;
	    }

		this.pageRecorders = pageRecorders;
		this.currentPage = currentPage;
		
		// ȡ����������
		this.totalRows = totalRows;

		// ȡ����ҳ��
		if ((totalRows % pageRecorders) == 0) {
			totalPages = totalRows / pageRecorders;
		} else {
			totalPages = totalRows / pageRecorders + 1;
		}
		totalPages = (totalPages == 0) ? 1 : totalPages;
		
		if (currentPage <= 0) {
			currentPage = 1;
		}

		// �ж��Ƿ�����һҳ
		if (currentPage >= totalPages) {
			this.currentPage = totalPages;
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		
		// �ж��Ƿ�����һҳ
		if (currentPage > 1) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
	}
}
