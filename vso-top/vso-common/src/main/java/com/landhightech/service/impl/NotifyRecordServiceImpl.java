package com.landhightech.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.landhightech.bean.NotifyReturnData;
import com.landhightech.mapper.NotifyRecordMapper;
import com.landhightech.service.INotifyRecordService;
import com.landhightech.util.MyBatisUtil;

public class NotifyRecordServiceImpl implements INotifyRecordService {
		public final Logger logger = LoggerFactory.getLogger(this.getClass());
		private static INotifyRecordService service = new NotifyRecordServiceImpl();

		private NotifyRecordServiceImpl() {
			super();
		}

		public static INotifyRecordService getInstance() {
			if (service == null) {
				service = new NotifyRecordServiceImpl();
			}
			return service;
		}
		
		public boolean insertRecord(NotifyReturnData data) {
			
			SqlSession ss = null;
			try {
				ss = MyBatisUtil.getSqlSession();
				NotifyRecordMapper mapper = ss
						.getMapper(NotifyRecordMapper.class);
				mapper.insertRecord(data);
				ss.commit();
			} catch (Exception e) {
				if(ss!=null)
					try {
						ss.rollback();
					} catch (Exception e1) {
						logger.error(e.getMessage());
					}
				logger.error("insert log to db:vso_notify_record error.{}",e.getMessage());
				return false;
			} finally {
				if(ss!=null)
					try {
						ss.close();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
			}
			
			return true;
		}


}
