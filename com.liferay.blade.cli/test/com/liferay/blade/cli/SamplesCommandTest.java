package com.liferay.blade.cli;

import static org.junit.Assert.assertFalse;

import aQute.lib.io.IO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author David Truong
 */
public class SamplesCommandTest {

	@Before
	public void setUp() throws Exception {
		File testdir = IO.getFile("generated/test");

		if (testdir.exists()) {
			IO.delete(testdir);
			assertFalse(testdir.exists());
		}
	}

	@Test
	public void testGetSample() throws Exception {
		String[] args = {
			"samples", "-d", "generated/test", "blade.friendlyurl"
		};

		new bladenofail().run(args);

		File projectDir = IO.getFile("generated/test/blade.friendlyurl");

		Assert.assertTrue(projectDir.exists());

		File buildFile = IO.getFile(projectDir, "build.gradle");

		Assert.assertTrue(buildFile.exists());
	}

	@Test
	public void testGetSampleWithDependencies() throws Exception {
		String[] args = {
			"samples", "-d", "generated/test", "blade.rest"
		};

		new bladenofail().run(args);

		File projectDir = IO.getFile("generated/test/blade.rest");

		Assert.assertTrue(projectDir.exists());

		File buildFile = IO.getFile(projectDir, "build.gradle");

		Assert.assertTrue(buildFile.exists());
	}

	@Test
	public void testListSamples() throws Exception {
		String[] args = {"samples"};

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);

		new bladenofail(ps).run(args);

		String content = baos.toString();

		Assert.assertTrue(content.contains("blade.portlet.ds"));
	}

}