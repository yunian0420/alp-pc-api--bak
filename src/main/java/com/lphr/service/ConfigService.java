package com.lphr.service;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lphr.dto.config.AddConfigDTO;
import com.lphr.dto.config.UpdateConfigDTO;
import com.lphr.entity.Config;
import com.lphr.enums.CodeEnum;
import com.lphr.manager.RedisManager;
import com.lphr.mapper.ConfigMapper;
import com.lphr.util.BeanMapper;
import com.lphr.util.StringUtil;
import com.lphr.vo.CommonVO;
import com.lphr.vo.ListConfigVO;

@Service
public class ConfigService {

	@Autowired
	private ConfigMapper configMapper;

	@Autowired
	private RedisManager redisManager;

	/**
	 * @author: YN
	 * @Date: 2019年4月24日下午3:21:20
	 * @TODO:
	 * @param:
	 * @return:
	 */
	public List<ListConfigVO> list() {
		return configMapper.findAll(0);
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月24日下午2:35:38
	 * @TODO:
	 * @param:
	 * @return:
	 */
	public CommonVO<Object> addConfig(AddConfigDTO dto) {
		CommonVO<Object> commonVO = new CommonVO<>();
		// 先判断KEY是不是纯数字
		if (StringUtil.isNumber(dto.getSysKey())) {
			commonVO.setMessage("添加失败，KEY不能为纯数字");
			commonVO.setCode(CodeEnum.CLIENT_ERROR.getCode());
			return commonVO;
		} else {
			Config config = BeanMapper.map(dto, Config.class);
			if (StringUtil.isNotEmpty(dto.getSysValue())) {
				redisManager.setObject(dto.getSysKey(), dto.getSysValue());
			}
			// 查询此Key是否已被占用
			Config findKey = new Config();
			findKey.setSysKey(dto.getSysKey());
			List<Config> list = configMapper.select(findKey);
			// 如果数据库中已存在此KEY，就修改失败
			if (list.size() > 0) {
				commonVO.setMessage("数据库中已存在此KEY，添加失败");
				commonVO.setCode(CodeEnum.CLIENT_ERROR.getCode());
				return commonVO;
			} else {
				configMapper.insertSelective(config);
				return commonVO;
			}
		}
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月24日下午3:34:19
	 * @TODO:
	 * @param:
	 * @return:
	 */
	public CommonVO<Object> updateConfig(@Valid UpdateConfigDTO dto) {
		CommonVO<Object> commonVO = new CommonVO<>();
		dto.setParentId(null);
		// 先判断KEY是不是纯数字
		if (StringUtil.isNumber(dto.getSysKey())) {
			commonVO.setMessage("修改失败，KEY不能为纯数字");
			commonVO.setCode(CodeEnum.CLIENT_ERROR.getCode());
			return commonVO;
		} else {
			Config config = BeanMapper.map(dto, Config.class);
			if (StringUtil.isNotEmpty(dto.getSysValue())) {
				redisManager.setObject(dto.getSysKey(), dto.getSysValue());
			}

			// 查询此Key是否已被占用
			Config findKey = new Config();
			findKey.setSysKey(dto.getSysKey());
			List<Config> list = configMapper.select(findKey);
			// 如果数据库中已存在此KEY，就修改失败
			if (list.size() > 0) {
				commonVO.setMessage("数据库中已存在此KEY，添加失败");
				commonVO.setCode(CodeEnum.CLIENT_ERROR.getCode());
				return commonVO;
			} else {
				configMapper.updateByPrimaryKeySelective(config);
				return commonVO;
			}
		}
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月24日下午3:43:47
	 * @TODO:
	 * @param:
	 * @return:
	 */
	public void deleteConfig(Integer id) {
		Config query = new Config();
		query.setParentId(id);
		configMapper.delete(query);
		configMapper.deleteByPrimaryKey(id);
	}

}
