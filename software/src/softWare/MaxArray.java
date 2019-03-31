package softWare;

import org.junit.*;

public class MaxArray {
	
	//求最大子数组
	public static int Max(int[] a) 
	{
		int max = a[0];
		for (int i = 0; i < a.length; i++) 
		{
			int temp = 0;
			for (int j = i; temp >= 0 && j < a.length; j++) 
			{
				temp += a[j];
				if (temp > max)
					max = temp;
			}
		}
		return max;
	}
	
	//求最大子数组改进版
	public static int Max2(int[] a) 
	{
		
		int max = a[0];
		int temp = 0;
		for (int i = 0; i < a.length; i++) 
		{
			if (temp >= 0) 
			{
				temp += a[i];
			}
			else 
			{
				temp = a[i];
			}
			if (temp > max)
				max = temp;
		}
		return max;
	}
	
	//求循环数组的最大子数组
	public static int Max3(int[] a) 
	{
		int max = a[0];
		for (int i = 0; i < a.length; i++) 
		{
			int temp = 0;
			for(int j = 0; j < a.length-1; j++) 
			{
				if (temp >= 0) 
				{
					temp += a[(i+j)%a.length];
					if (temp > max)
						max = temp;
				}
				else 
				{
					break;
				}
			}
		}
		return max;
	}
	
	//测试
	@Test
	public void test() 
	{
		int[] a = {20,-5,30};
		Assert.assertEquals(Max(a), 45);
		Assert.assertEquals(Max3(a), 50);
	}
	
	//测试
	@Test
	public void test2() 
	{
		int[] b = {2,-1,3,-2,9};
		Assert.assertEquals(Max(b), 11);
		Assert.assertEquals(Max2(b), 11);
		Assert.assertEquals(Max3(b), 13);
	}
	
	//测试
	@Test
	public void test3() 
	{
		int[] b = {-1,3,-2};
		Assert.assertEquals(Max(b), 3);
		Assert.assertEquals(Max2(b), 3);
		Assert.assertEquals(Max3(b), 3);
	}
	
	//测试
	@Test
	public void test4() 
	{
		int[] b = {-1,-3,-2};
		Assert.assertEquals(Max(b), -1);
		Assert.assertEquals(Max2(b), -1);
		Assert.assertEquals(Max3(b), -1);
	}
	
	//测试
	@Test
	public void test5() 
	{
		int[] b = {-5,-3,-2};
		Assert.assertEquals(Max(b), -2);
		Assert.assertEquals(Max2(b), -2);
	}
	
}
