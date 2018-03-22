import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		// compl�ter

		for (int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++)
			{
				this.imageData[i][j] = (this.imageData[i][j]).Negative();
			}
		}

	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// compl�ter
		for (int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++)
			{
				this.imageData[i][j] = (this.imageData[i][j]).toBWPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		// compl�ter
		for (int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++)
			{
				this.imageData[i][j] = (this.imageData[i][j]).toGrayPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// compl�ter
		for (int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++)
			{
				this.imageData[i][j] = (this.imageData[i][j]).toColorPixel();
			}
		}
		
	}
	
	public void convertToTransparentImage()
	{
		// compl�ter
		for (int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++)
			{
				this.imageData[i][j] = (this.imageData[i][j]).toTransparentPixel();
			}
		}
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		// compléter
		int xr=0,yr=0;
		//Creation d'une image de meme type avec les memes dimensions
		PixelMapPlus temp = new PixelMapPlus(this.imageType,height,width);
		//On copie la matrice originale
		for(int i=0;i<temp.height;i++)
			for(int j=0;j<temp.width;j++) {
				temp.imageData[i][j]=this.imageData[i][j];

			}
		//REmplacement des pixels par les pixels rotates
		for(int i=0;i<temp.height;i++) {
			for (int j = 0; j < temp.width; j++) {
				//On met le pixel a blanc par defaut
				imageData[i][j] = new ColorPixel();

				//Calcul des indices correspondant sur l'image originale
				yr = (int) ((i - x) * Math.cos(angleRadian) + (y - j) * Math.sin(angleRadian) + x);
				xr = (int) ((j - y) * Math.cos(angleRadian) + (i - x) * Math.sin(angleRadian) + y);
				//Verfication de la validite des indices
				if (yr >= 0 && yr < height && xr >= 0 && xr < width)
					imageData[i][j] = temp.imageData[yr][xr];

			}
		}
		// Liberation du temp
		temp.clearData();
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException //A refaire avec un pixel map
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		// compl�ter
		//1.Declaration de la nouvelle map
		PixelMapPlus rescaled = new PixelMapPlus(this.imageType,h,w);
		AbstractPixel[][] rescaledImage = new AbstractPixel[h][w];
		//2.Calcul de l'échelle selon chaque axe
		double scaleH = (double)this.height/(double)h;
		double scaleW = (double)this.width/(double)w;
		//3.Algorithme: De mise à l'échelle
		for(int row = 0; row < h; row++){
			for(int col = 0; col < w ; col++){

				rescaled.imageData[row][col] =
						this.imageData[(int)(((double)row)*scaleH)][(int)(((double)col)*scaleW)];
				//Cas 1:scaleH ou scaleW > 1 ==>  réduction de taille, on copie 1 pixel sur x pixels (ratio de taille)
				//Cas 2: scaleH ou scaleW < 1 ==> augmentation de taille, on extrapole donc on copie le même pixel x fois.
			}
		}

		//4. Désallocation de l'image actuelle
		this.clearData();

		//5. Faire pointer this.imageData vers la nouvelle image redimentionne:
		this.imageData = rescaled.imageData;
		height = h;
		width = w;
	}
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// completer
		//1.Vérifier que les dimensions de la matrice ne seront pas dépassés
		if(row0 < 0 || col0 < 0)
			throw new IllegalArgumentException();
		int rowMax = ((row0 + pm.height)< this.height) ? (row0 + pm.height):this.height;
		int colMax = ((col0 + pm.width)< this.width) ? (col0 + pm.width):this.width;
		//2.Copier la matrice pm dans l'image actuelle
		for(int row=row0; row<rowMax; row++)
		{
			for(int col = col0; col < colMax; col++)
			{
				this.imageData[row][col] = pm.imageData[row-row0][col-col0];
				//Note: on soustrait les indices de depart row0 et col0 car du
				// pour l'image pm, il faut commencer a 0, 0.
			}
		}

	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		// compl�te
		//1. Allocation de mémoire pour l'image croped
		PixelMapPlus croped = new PixelMapPlus(this.imageType,h,w);

		//Par défaut ca creer une image blanche
		for(int row=0; row<h; row++)
		{
			for(int col = 0; col < w; col++)
			{
				croped.imageData[row][col] = new ColorPixel(); //Par defaut c'est blanc

			}
		}

		//2. Pour eviter des acces out of bound: Delimiter les limites de recopiage
		// ==> cas 1: La dimension de la cropedImage est plus petite que celle de l'objet courant
		//         ==> On ne recopie que jusqu'à la dimension de la cropedImage
		// ==> cas 2: La dimension de la cropedImage est plus grande que celle de l'objet courant
		//         ==> On ne recopie que jusqu'à la dimension de l'objet courant

		int rowMax = (this.height < h) ? this.height:h;
		int colMax = (this.width < w) ? this.width:w;

		//2.Copier la region d'interet dans l'image croped
		for(int row=0; row<rowMax; row++)
		{
			for(int col = 0; col < colMax; col++)
			{

				croped.imageData[row][col] = this.imageData[row][col];
			}
		}
		//3. Desallocation de la matrice actuelle
		this.clearData();
		//4. Faire pointer this.imageData vers cropedImage.imageData:
		height = h;
		width = w;
		this.imageData = croped.imageData;

	}

//	/**
//	 * Effectue une translation de l'image
//	 */
public void translate(int rowOffset, int colOffset)
{
	// compl�ter
	//1. Allocation de mémoire pour l'image translatée (qui aura les mêmes dimensions
	// que la première

	PixelMapPlus shifted = new PixelMapPlus(this.imageType,this.height,this.width);
	//2. Par défaut on remplit avec des pixels blancs
	for(int row=0; row<height; row++)
	{
		for(int col = 0; col < width; col++)
		{

			shifted.imageData[row][col] = new ColorPixel(); // blancs par défaut
		}
	}

	// 2. Définir les frontières de la zone à copier
	int row0 = (rowOffset > 0) ? rowOffset: 0;
	int col0 = (colOffset > 0) ? colOffset: 0;
	int rowMax = (rowOffset > 0) ? height : (height + rowOffset); //Rappel le offset est négatif
	int colMax = (colOffset > 0)  ? width : (width + colOffset);

	//2.2.Copier la matrice pm dans l'image actuelle
	for(int row=row0; row<rowMax; row++)
	{
		for(int col = col0; col < colMax; col++)
		{

			shifted.imageData[row-rowOffset][col-colOffset] = this.imageData[row][col];
		}
	}
	//3. Desallocation de la matrice actuelle
	this.clearData();
	//4. Copie:
	this.imageData = shifted.imageData;

}

	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException //A refaire avec celui de Fatou
	{


		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();

		// 1. On détermine les dimensions de la region de l'image qui sera preservée
		int hauteurCadre= (int) (height/zoomFactor);
		int largeurCadre= (int) (width/zoomFactor);

		//2. On détermine le début de la région à recopier et l'ajuste pour éviter
		// les accès out of bound
		int col0= 0;
		int row0 = 0;


			//Vérification de la limite inférieure du cadre de recopiage
		if(x-largeurCadre/2>0)
			col0=x-largeurCadre/2;
		if(y-hauteurCadre/2>0)
			row0=y-hauteurCadre/2;

			//Vérification de la limite supérieur du cadre de recopiage
		if(x+largeurCadre/2>width)
			col0=width-largeurCadre;
		if(y+hauteurCadre/2>height)
			row0=height-hauteurCadre;

		// 3. Définition de la nouvelle image zoomed:
		PixelMapPlus zoomed = new PixelMapPlus(this.imageType,this.height,this.width);

		//4. On boucle uniquement sur la partie du cadre

		for (int row = 0; row < height; row++)
		{
			for(int col = 0; col< width; col++)
			{
				// Extrapolation pour faire le resizing en meme temps:
				zoomed.imageData[row][col] = this.imageData[(row0 + (int)((double)row/zoomFactor))]
															[(col0 + (int)((double)col/zoomFactor))];

			}

		}
		//Desallocation de la matrice actuelle et copie
		this.clearData();
		this.imageData = zoomed.imageData;
	}


}
