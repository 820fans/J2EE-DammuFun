package com.yida.common;

public class JSONs {
	
	public CheckJson CreaateCheckJson(){
		return new CheckJson();
	}
	
	/**
	 * 返回某个视频或者图像文件已经上传的内容所占百分比
	 *
	 */
	public class CheckJson{
		public String id;

		public int percent;

		public int loadedSize;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}

		public int getLoadedSize() {
			return loadedSize;
		}

		public void setLoadedSize(int loadedSize) {
			this.loadedSize = loadedSize;
		}
	}
}

