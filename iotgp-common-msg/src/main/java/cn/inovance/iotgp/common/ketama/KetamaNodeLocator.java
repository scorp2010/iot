package cn.inovance.iotgp.common.ketama;

import java.io.Serializable;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public final class KetamaNodeLocator implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TreeMap<Long, Node> ketamaNodes;
	private HashAlgorithm hashAlg;
	private int numReps = 160;
	private Set<Node> serverNodes;

	public Set<Node> getServerNodes() {
		return serverNodes;
	}

	public void setServerNodes(Set<Node> serverNodes) {
		this.serverNodes = serverNodes;
	}

	public KetamaNodeLocator(Set<Node> nodes, HashAlgorithm alg, int nodeCopies) {
		serverNodes = nodes;
		hashAlg = alg;
		ketamaNodes = new TreeMap<Long, Node>();

		numReps = nodeCopies;

		for (Node node : nodes) {
			for (int i = 0; i < numReps / 4; i++) {
				// node.getName()对应服务器的账号
				byte[] digest = hashAlg.computeMd5(node.getName() + i);
				for (int h = 0; h < 4; h++) {
					long m = hashAlg.hash(digest, h);

					ketamaNodes.put(m, node);
				}
			}
		}
	}

	public Node getPrimary(final String k) {
		byte[] digest = hashAlg.computeMd5(k);
		Node rv = getNodeForKey(hashAlg.hash(digest, 0));
		return rv;
	}

	Node getNodeForKey(long hash) {
		if (ketamaNodes.isEmpty())
			return null;
		final Node rv;
		Long key = hash;
		// 如果找到这个节点，直接取节点，返回
		if (!ketamaNodes.containsKey(key)) {
			// 得到大于当前key的那个子Map，然后从中取出第一个key，就是大于且离它最近的那个key
			SortedMap<Long, Node> tailMap = ketamaNodes.tailMap(key);
			if (tailMap.isEmpty()) {
				key = ketamaNodes.firstKey();
			} else {
				key = tailMap.firstKey();
			}
			// For JDK1.6 version
			// key = ketamaNodes.ceilingKey(key);
			// if (key == null) {
			// key = ketamaNodes.firstKey();
			// }
		}
		rv = ketamaNodes.get(key);
		return rv;
	}
}
