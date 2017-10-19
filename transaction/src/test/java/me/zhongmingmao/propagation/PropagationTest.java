package me.zhongmingmao.propagation;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 检验事务的传播行为
 *
 * @author zhongmingmao zhongmingmao0625@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagationTest {
    
    // ===== 1. REQUIRED：默认
    
    /**
     * REQUIRED：默认的事务传播行为<p>
     * 表示当前方法必须运行在事务中，如果当前事务存在，方法将会在该事务中运行，否则，会启动一个新的事务
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void required() {
    }
    
    // ===== 2. MANDATORY & NEVER：强制有或没有活动事务
    
    /**
     * MANDATORY<p>
     * 表示该方法必须在事务中运行，如果当前事务不存在，则会抛出一个异常
     */
    @Ignore("No existing transaction found for transaction marked with propagation 'mandatory'")
    @Test
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryWithoutTxContextTest() {
        // 执行这个单元测试时，不存在事务，直接抛出异常IllegalTransactionStateException
        // 跳过测试
    }
    
    /**
     * NEVER<p>
     * 表示当前方法不应该运行在事务上下文中，如果当前正有一个事务在运行，则会抛出异常
     */
    @Transactional(propagation = Propagation.NEVER)
    public void never() {
    }
    
    // ===== 3. SUPPORTS & NOT_SUPPORTED : 非强制有或没有活动事务
    
    /**
     * SUPPORTS<p>
     * 表示当前方法不需要事务上下文，但是如果存在当前事务的话，那么该方法会在这个事务中运行
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supports() {
    }
    
    /**
     * NOT_SUPPORTED<p>
     * 表示该方法不应该运行在事务中，如果存在当前事务，在该方法运行期间，当前事务将被挂起
     * 需要使用JtaTransactionManager
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupported() {
    }
    
    // ===== 4. REQUIRES_NEW & NESTED ：独立事务与嵌套事务
    
    /**
     * REQUIRES_NEW<p>
     * 表示当前方法必须运行在它自己的事务中，一个新的事务将被启动，如果存在当前事务，在该方法执行期间，当前事务会被挂起
     * 需要使用JtaTransactionManager
     * 不是真正的嵌套事务，外层事务与内层事务相互独立
     * 内层事务会独立的提交，不依赖于外层事务
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew() {
    }
    
    
    /**
     * NESTED<p>
     * 如果一个活动的事务存在，则运行在一个嵌套的事务中，如果没有活动事务, 则按PROPAGATION_REQUIRED属性执行
     * 需要将AbstractPlatformTransactionManager.nestedTransactionAllowed=true
     * 真正的嵌套事务，外层事务失败会导致内层事务回滚，但内层事务失败不会导致外层事务回滚，而是恢复到上一个恢复点
     * 内层事务是外层事务的一部分，只有外层事务结束后才会被提交
     */
    @Transactional(propagation = Propagation.NESTED)
    public void nested() {
    }
    
    
    // ============================================
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredWithTxContextTest() {
        // 执行这个单元测试时，不存在事务，开启一个新事务
        // 执行required()发现已经存在事务，加入到该事务
        required();
    }
    
    @Test
    public void requiredWithoutTxContextTest() {
        // 执行required()发现不存在事务，开启一个新事务
        required();
    }
    
    // ============================================
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void supportsWithTxContextTest() {
        // 执行这个单元测试时，不存在事务，开启一个新事务
        // 执行supports()发现已经存在事务，加入到该事务
        supports();
    }
    
    @Test
    public void supportsWithoutTxContextTest() {
        // 执行supports()发现不存在事务，以非事务的方式直接执行supports()
        supports();
    }
    
    
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void notSupportedWithTxContextTest() {
        // 执行这个单元测试时，不存在事务，开启一个新事务A
        // 执行notSupported()前，先挂起当前事务A，然后以非事务的形式直接执行notSupported()
        notSupported();
        // 重启事务A，继续后续操作
    }
    
    // ============================================
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiresNewWithTxContextTest() {
        // 执行这个单元测试时，不存在事务，开启一个新事务A
        // 执行requiresNew()前，先挂起当前事务A，然后开启一个新事务B
        requiresNew();
        // 提交或回滚事务B，重启事务A，继续后续操作，最后提交或回滚事务A
        //
        // 事务A称为外层事务，事务B称为内层事务
        // 关键概念：外层事务和内层事务是相互独立的事务！！
        // 内层事务会独立的提交，不依赖于外层事务
    }
    
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void nestedWithTxContextTest() {
        // 执行这个单元测试时，不存在活动事务，开启一个新事务A
        // 在执行nested()之前，保存当前状态到savepoint S，如果nested()执行失败，恢复到S
        // 开启一个新事务B，为事务A的嵌套事务
        nested();
        // 此时内层事务B尚未被提交
        //
        // 事务A称为外层事务，事务B称为内层事务
        // 关键概念：外层事务失败，会回滚内层事务，但内层事务失败，不会引起外层事务的回滚，只会恢复到上一个恢复点！！
        // 内层事务是外层事务的一部分，只有外层事务结束后才会被提交
    }
    
    @Test
    public void nestedWithoutTxContextTest() {
        // 执行这个单元测试时，不存在活动事务，与REQUIRED的执行逻辑一致
        nested();
    }
}