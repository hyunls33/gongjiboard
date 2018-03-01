package com.mycom.dto;

import java.lang.Math;

public class RPageVO {
	public static final int PAGE_SCALE = 10; // �������� �Խù� ��
    public static final int BLOCK_SCALE = 10; // ��ϴ� ��������
 
    private int curPage;    //���� ������ ��ȣ
    private int prevPage;   //���� ������
    private int nextPage;   //���� ������
    private int totPage;    //��ü ������ ����
    private int curBlock;   //���� ������ ��� ��ȣ
    private int totBlock;   //��ü ������ ��� ����
    private int pageBegin;  //������ �������� ���ڵ� ���� ��ȣ
    private int pageEnd;    //������ �������� ���ڵ� ������ ��ȣ
    private int blockStart; //������ ��� �������� ���� ������ ��ȣ
    private int blockEnd;   //������ ��� �������� ������ ������ ��ȣ
 
    public int getCurPage() {
        return curPage;
    }
 
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
 
    public int getPrevPage() {
        return prevPage;
    }
 
    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }
 
    public int getNextPage() {
        return nextPage;
    }
 
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
 
    public int getTotPage() {
        return totPage;
    }
 
    public void setTotPage(int count) {
 
        totPage = (int) Math.ceil(count * 1.0 / PAGE_SCALE);
 
    }
 
    public int getCurBlock() {
        return curBlock;
    }
 
    public void setCurBlock(int curBlock) {
        this.curBlock = curBlock;
    }
 
    public int getTotBlock() {
        return totBlock;
    }
 
    public void setTotBlock(int totBlock) {
        this.totBlock = totBlock;
    }
 
    public int getPageBegin() {
        return pageBegin;
    }
 
    public void setPageBegin(int pageBegin) {
        this.pageBegin = pageBegin;
    }
 
    public int getPageEnd() {
        return pageEnd;
    }
 
    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
 
    public int getBlockStart() {
        return blockStart;
    }
 
    public void setBlockStart(int blockStart) {
        this.blockStart = blockStart;
    }
 
    public int getBlockEnd() {
        return blockEnd;
    }
 
    public void setBlockEnd(int blockEnd) {
        this.blockEnd = blockEnd;
    }
 
    // ������
    public RPageVO(int count, int curPage) {
 
        curBlock = 1;             // ���� ������ ����� 1�� ����
        this.curPage = curPage;   // ���� ������ ��ȣ ����
        setTotPage(count);        // ��ü ������ ���� ����
        setPageRange();           // ���� ������ ���۹�ȣ, ����ȣ ���
        setTotBlock();            // ��ü ������ ��� ���� ���
        setBlockRange();          // ���� ������ ����� ���� ������ �������� ��ȣ ���
    }
 
    // ���� �������� ���° �������� ���ϴ��� ���
    public void setBlockRange() {
    	
    	//���� �������� ��ü������ �������� ũ�� ���� ��� ������ �������� ó���ϱ�
    	if (curPage > totPage) {
    		this.curPage = totPage;
    	}
    	
        //���� �������� ���° ������ ��Ͽ� ���ϴ��� ���
        curBlock = (int) Math.ceil((curPage - 1) / BLOCK_SCALE) + 1; 
        blockStart = (curBlock - 1) * BLOCK_SCALE + 1;  //���۹�ȣ
 
        blockEnd = blockStart + BLOCK_SCALE - 1;        //����ȣ
 
        if (blockEnd > totPage) {                       //������ �������� ������ �ʰ��� ���
            blockEnd = totPage;
        }
        
        //���� ����� 1�̸� ���� �������� 1�� ����
        prevPage = curBlock == 1 ? 1 : (curBlock - 1) * BLOCK_SCALE;
 
        //���� ����� ������ ����̸� ���� �������� ������ ������ ��ȣ�� ����
        nextPage = curBlock > totBlock ? (curBlock * BLOCK_SCALE) : (curBlock * BLOCK_SCALE) + 1;
 
        //������ �������� ������ ���� �ʵ��� ó��
        if (nextPage >= totPage) {
 
            nextPage = totPage;
        }
    }
 
    // ��ü ������ ��� ���� ���
    public void setTotBlock() {
 
        totBlock = (int) Math.ceil(totPage * 1.0 / BLOCK_SCALE);
 
    }
 
    // ������������ ���۹�ȣ, ����ȣ ���
    public void setPageRange() {
    	//pageBegin = (curPage - 1) * PAGE_SCALE + 1;//����Ŭ�� ��� between�� ����ϸ� +1�� ����� ������? �ϴ� mysql������ �ʿ� ����
    	
        if (curPage > totPage) {
        	//���� �Է¹��� ������ ��ȣ�� ��ü �������� ������ �ʰ��� ���, ������ �������� ��ȯ��
        	this.pageBegin = (totPage - 1) * PAGE_SCALE;
        } else {
        	//�ƴ� ��쿡�� �Է¹��� ������������ ��ȯ
        	this.pageBegin = (curPage - 1) * PAGE_SCALE;
        }
        
        pageEnd = pageBegin + PAGE_SCALE - 1;
 
    }


}
