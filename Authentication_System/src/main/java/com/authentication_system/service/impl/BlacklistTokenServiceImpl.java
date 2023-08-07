package com.authentication_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication_system.entity.BlacklistToken;
import com.authentication_system.repository.BlacklistTokenRepository;
import com.authentication_system.service.BlacklistTokenService;

/**
 * BlacklistTokenService implementation which provides functionality over blacklisted tokens
 */
@Service
public class BlacklistTokenServiceImpl implements BlacklistTokenService {

	@Autowired
	private BlacklistTokenRepository blacklistTokenRepository;

	@Override
	public void addTokenToBlacklist(String token) {
		BlacklistToken blacklistEntity = new BlacklistToken();
        blacklistEntity.setToken(token);
        blacklistTokenRepository.save(blacklistEntity);
	}

	@Override
	public boolean isTokenBlacklisted(String token) {
		BlacklistToken resultToken = blacklistTokenRepository.findByToken(token);
		if (resultToken==null) {
			return false;
		}
		return true;
	}
}
