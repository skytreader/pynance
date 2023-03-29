package net.skytreader.pynance.service;

import net.skytreader.pynance.logic.Config;
import net.skytreader.pynance.model.InstallationConfig;
import net.skytreader.pynance.repository.InstallationConfigRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StartUpServiceTest {

    @Test
    void testIsInstallationCompleteTrue() {
        InstallationConfigRepository icr = new InstallationConfigRepository() {
            @Override
            public List<InstallationConfig> fetchConfig(Collection<String> keys) {
                LinkedList<InstallationConfig> configs = new LinkedList<InstallationConfig>();
                configs.add(new InstallationConfig(Config.KEY_NET_MONTHLY,
                        "100"));
                configs.add(new InstallationConfig(Config.KEY_LIVING_COST_PERCENT, "50"));
                configs.add(new InstallationConfig(Config.KEY_ALLOWANCE_PERCENT, "50"));
                configs.add(new InstallationConfig(Config.KEY_PROJECTED_LIMIT_UTILITIES, "10"));
                configs.add(new InstallationConfig(Config.KEY_PROJECTED_LIMIT_FOOD, "10"));
                return configs;
            }

            @Override
            public List<InstallationConfig> findAll() {
                return null;
            }

            @Override
            public List<InstallationConfig> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<InstallationConfig> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends InstallationConfig> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends InstallationConfig> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends InstallationConfig> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<InstallationConfig> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public InstallationConfig getOne(Long aLong) {
                return null;
            }

            @Override
            public InstallationConfig getById(Long aLong) {
                return null;
            }

            @Override
            public InstallationConfig getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends InstallationConfig> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends InstallationConfig> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<InstallationConfig> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends InstallationConfig> S save(S entity) {
                return null;
            }

            @Override
            public Optional<InstallationConfig> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(InstallationConfig entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends InstallationConfig> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends InstallationConfig> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends InstallationConfig> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends InstallationConfig> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends InstallationConfig> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends InstallationConfig, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
        StartUpService sss = new StartUpService(icr);
        assertTrue(sss.isInstallationComplete());
    }
}
