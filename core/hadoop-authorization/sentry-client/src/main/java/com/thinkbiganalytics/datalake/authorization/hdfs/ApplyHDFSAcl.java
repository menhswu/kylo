package com.thinkbiganalytics.datalake.authorization.hdfs;

import com.thinkbiganalytics.datalake.authorization.client.SentryClientConfig;

import org.apache.hadoop.conf.Configuration;

import java.io.IOException;

/**
 * Created by Shashi Vishwakarma on 19/9/2016.
 */

public class ApplyHDFSAcl {

    public ApplyHDFSAcl() {

    }

    /**
     * @param category_name    : Category Name
     * @param feed_name        : Feed Name
     * @param permission_level : Feed Permission Level
     * @param groupList        : Group List for Authorization
     */

    @SuppressWarnings("static-access")
    public boolean createAcl(String HadoopConfigurationResource, String category_name, String feed_name, String permission_level, String groupList, String hdfs_permission) throws IOException {

        try {

            SentryClientConfig sentryConfig = new SentryClientConfig();
            HDFSUtil hdfsUtil = new HDFSUtil();

            Configuration conf = sentryConfig.getConfig();
            conf = hdfsUtil.getConfigurationFromResources(HadoopConfigurationResource);

            String allPathForAclCreation = hdfsUtil.constructResourceforPermissionHDFS(category_name, feed_name, permission_level);
            hdfsUtil.splitPathListAndApplyPolicy(allPathForAclCreation, conf, sentryConfig.getFileSystem(), groupList, hdfs_permission);

            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException("Unable to create ACL " + e);

        }
    }

    @SuppressWarnings("static-access")
    public boolean flushACL(String HadoopConfigurationResource, String category_name, String feed_name, String permission_level) throws IOException {

        try {

            SentryClientConfig sentryConfig = new SentryClientConfig();
            HDFSUtil hdfsUtil = new HDFSUtil();

            Configuration conf = sentryConfig.getConfig();
            conf = hdfsUtil.getConfigurationFromResources(HadoopConfigurationResource);

            String allPathForAclCreation = hdfsUtil.constructResourceforPermissionHDFS(category_name, feed_name, permission_level);
            hdfsUtil.splitPathListAndFlushPolicy(allPathForAclCreation, conf, sentryConfig.getFileSystem());

            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException("Unable to create ACL " + e);

        }

    }

}
